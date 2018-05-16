package com.slyak.mirrors.service;

import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPOutputStream;
import com.slyak.core.util.SSH2;
import com.slyak.core.util.StdCallback;
import com.slyak.core.util.StringUtils;
import com.slyak.file.FileStoreService;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.repository.*;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
@Service
@Slf4j
public class MirrorManagerImpl implements MirrorManager {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ScriptFileRepository scriptFileRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProjectHostRepository projectHostRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private FileStoreService<String> fileStoreService;

    private static final String ENV_PATTERN = "\\$[{]?([A-Za-z0-9_-]+)[}]?";

    @Override
    public Page<Project> queryProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public List<HostGroupScript> getGroupScripts(Long groupId) {
        return null;
    }

    @Override
    public HostGroup findGroup(Long groupId) {
        return groupRepository.findOne(groupId);
    }

    @Override
    public List<ProjectHost> findGroupHosts(Long groupId) {
        return projectHostRepository.findByGroupId(groupId);
    }

    @Override
    public void execGroupScripts(Long groupId, GroupScriptCallback callback) {
        List<ProjectHost> projectHosts = findGroupHosts(groupId);
        List<HostGroupScript> hostGroupScripts = getGroupScripts(groupId);
        for (ProjectHost projectHost : projectHosts) {
            SSH2 ssh2 = SSH2.connect(projectHost.getHost(), projectHost.getSshPort()).auth(projectHost.getUser(), projectHost.getPassword());
            for (HostGroupScript hostGroupScript : hostGroupScripts) {
                execCommand(ssh2, "sh -c " + hostGroupScript.getScriptFile().getScpPath(), new StdCallback() {
                    @Override
                    public void processOut(String out) {
                        callback.processOut(projectHost, hostGroupScript, out);
                    }

                    @Override
                    public void processError(String error) {
                        callback.processError(projectHost, hostGroupScript, error);
                    }
                });
            }
        }
    }

    @Override
    public List<ScriptFile> findScriptFiles(Long scriptId) {
        return scriptFileRepository.findByScriptId(scriptId);
    }


    private Script findScript(Long id) {
        return scriptRepository.findOne(id);
    }

    @Override
    public void saveScript(Script script) {
        generateScriptEnvs(script);
        scriptRepository.save(script);
    }

    private void generateScriptEnvs(Script script) {
        String content = script.getContent();
        Set<String> envs = content == null ? Collections.emptySet() : new HashSet<>(StringUtils.findGroupsIfMatch(ENV_PATTERN, content));
        List<ScriptEnv> existEnvs = script.getEnvs();//null or empty
        if (envs.isEmpty() || CollectionUtils.isEmpty(existEnvs)) {
            script.setEnvs(toScriptEnvs(envs));
        } else {
            //remove not exist key
            for (int i = existEnvs.size() - 1; i >= 0; i--) {
                ScriptEnv scriptEnv = existEnvs.get(i);
                if (envs.contains(scriptEnv.getKey())) {
                    envs.remove(scriptEnv.getKey());
                } else {
                    existEnvs.remove(i);
                }
            }
            //add new key
            existEnvs.addAll(toScriptEnvs(envs));
        }
    }

    private List<ScriptEnv> toScriptEnvs(Set<String> envs) {
        if (CollectionUtils.isEmpty(envs)) {
            return Collections.emptyList();
        }
        return envs.stream().map(s -> new ScriptEnv(s, null, null)).collect(Collectors.toList());
    }


    @Override
    public Page<Script> queryScripts(String keyword, Pageable pageable) {
        return scriptRepository.queryByNameLike(StringUtils.isEmpty(keyword) ? null : "%" + keyword + "%", pageable);
    }

    @Override
    public void saveOs(OS os) {
        osRepository.save(os);
    }

    @Override
    public void saveScriptFile(ScriptFile scriptFile) {
        scriptFileRepository.save(scriptFile);
    }

    @Override
    public List<OS> queryOss() {
        return osRepository.findAll();
    }

    @Override
    public OS findOs(String osName) {
        return osRepository.findByOs(osName);
    }

    @Override
    public void execScript(Long id, String description) {
        execScripts(Collections.singletonList(id), description);
    }

    private void execScripts(List<Long> scripts, String description) {
        runBatch(createBatch(scripts, description));
    }

    @SneakyThrows
    private void runBatch(Batch batch) {
        SSH2 ssh2 = SSH2.connect("", 22).auth("root", "123456");
        for (Long scriptId : batch.getScriptIds()) {
            List<ScriptFile> files = findScriptFiles(scriptId);
            //do scp, if file exist skip
            for (ScriptFile sf : files) {
                GlobalFile gf = sf.getGlobalFile();
                File nativeFile = fileStoreService.lookup(gf.getId());
                SCPClient scpClient = ssh2.scpClient();

                @Cleanup FileInputStream in = new FileInputStream(nativeFile);
                @Cleanup SCPOutputStream out = scpClient.put(gf.getName(), gf.getSize(), sf.getScpPath(), "7777");
                //TODO continues copy
                IOUtils.copy(in, out);
                out.flush();
            }

        }
    }

    private Batch createBatch(List<Long> scriptIds, String description) {
        Batch batch = new Batch();
        batch.setDescription(description);
        batch.setScriptIds(scriptIds);
        return batchRepository.save(batch);
    }

    private void execCommand(SSH2 ssh2, String command, StdCallback callback) {
        try {
            ssh2.execCommand(command, callback);
        } finally {
            log.info("closing ssh connection");
            ssh2.disconnect();
        }
    }

}