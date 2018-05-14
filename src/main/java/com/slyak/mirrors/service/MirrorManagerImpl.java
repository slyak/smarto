package com.slyak.mirrors.service;

import com.slyak.core.util.SSH2;
import com.slyak.core.util.StdCallback;
import com.slyak.core.util.StringUtils;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private ScriptEnvRepository scriptEnvRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProjectHostRepository projectHostRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private OSRepository osRepository;

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

    @Override
    public List<ScriptEnv> findScriptEnvs(Long scriptId) {
        return scriptEnvRepository.findByScriptId(scriptId);
    }

    @Override
    public void saveScript(Script script) {
        scriptRepository.save(script);
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

    private void execCommand(SSH2 ssh2, String command, StdCallback callback) {
        try {
            ssh2.execCommand(command, callback);
        } finally {
            log.info("closing ssh connection");
            ssh2.disconnect();
        }
    }

}