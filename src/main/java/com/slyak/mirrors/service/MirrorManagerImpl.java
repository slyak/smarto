package com.slyak.mirrors.service;

import com.google.common.collect.Maps;
import com.slyak.core.ssh2.CustomStdLogger;
import com.slyak.core.ssh2.SSH2;
import com.slyak.core.util.LoggerUtils;
import com.slyak.core.util.StringUtils;
import com.slyak.file.FileStoreService;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.repository.*;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;
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

    private static final String ENV_PATTERN = "\\$[{]?([A-Za-z0-9_-]+)[}]?";

    private static final String INIT_SCRIPT = "__init.sh";

    private static final String COMMAND_PREFIX = "sh -c ";

    private static final String USER_HOME = "~";

    private static final char SEPARATOR = '/';

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final Object BATCH_RESULT_LOCK = new Object();

    private final ProjectRepository projectRepository;

    private final ScriptFileRepository scriptFileRepository;

    private final GroupRepository groupRepository;

    private final ProjectHostRepository projectHostRepository;

    private final ScriptRepository scriptRepository;

    private final OSRepository osRepository;

    private final BatchRepository batchRepository;

    private final HostRepository hostRepository;

    private final GlobalRepository globalRepository;

    private final FileStoreService<String> fileStoreService;

    @Autowired
    public MirrorManagerImpl(
            ProjectRepository projectRepository,
            ScriptFileRepository scriptFileRepository,
            GroupRepository groupRepository,
            ProjectHostRepository projectHostRepository,
            ScriptRepository scriptRepository,
            OSRepository osRepository,
            BatchRepository batchRepository,
            HostRepository hostRepository,
            GlobalRepository globalRepository,
            FileStoreService<String> fileStoreService
    ) {
        this.projectRepository = projectRepository;
        this.scriptFileRepository = scriptFileRepository;
        this.groupRepository = groupRepository;
        this.projectHostRepository = projectHostRepository;
        this.scriptRepository = scriptRepository;
        this.osRepository = osRepository;
        this.batchRepository = batchRepository;
        this.hostRepository = hostRepository;
        this.globalRepository = globalRepository;
        this.fileStoreService = fileStoreService;
    }

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
        Set<String> envs
                = content == null ?
                Collections.emptySet() : new HashSet<>(StringUtils.findGroupsIfMatch(ENV_PATTERN, content));
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
    public void testExecScript(Long scriptId) {
        Host host = getGlobalTestHost();
        runBatch(createBatch(Collections.singletonList(scriptId), Collections.singletonList(host.getId())));
    }

    @Override
    public void execScripts(List<Long> scriptIds, List<Long> hostIds) {
        runBatch(createBatch(scriptIds, hostIds));
    }

    private Global getGlobal() {
        Global global = globalRepository.findOne(Global.ONLY_ID);
        return global == null ? new Global() : global;
    }

    @Override
    public Host getGlobalTestHost() {
        Global global = getGlobal();
        Assert.notNull(global, "Global config must be set");
        return hostRepository.findOne(global.getHostId());
    }

    @SneakyThrows
    private void runBatch(Batch batch) {
        Global global = getGlobal();
        List<Host> hosts = hostRepository.findAll(batch.getHostIds());
        List<Script> scripts = scriptRepository.findAll(batch.getScriptIds());
        String homePath = global.getHomePath();
        Long batchId = batch.getId();
        for (Host host : hosts) {
            //run command in one host
            String logFile = getBatchLogfile(homePath, batchId, host.getId());
            asyncConnectExecute(batchId, host, scripts, logFile);
        }
    }

    @Async
    public void asyncConnectExecute(Long batchId, Host host, List<Script> scripts, String logfile) {
        //connect and auth
        SSH2 ssh2 = connectToHost(host);
        //custom std logger
        CustomStdLogger stdLogger = new CustomStdLogger(LoggerUtils.createLogger(logfile));
        try {
            for (Script script : scripts) {
                //copy script files
                copyScriptFiles(script.getId(), ssh2);
                String bashScript = script.getContent();
                //run bash script
                runBashScript(ssh2, bashScript, stdLogger);
            }
        } catch (Exception e) {
            log.error("An error occurred : {}", e);
        } finally {
            ssh2.disconnect();
        }
        //log batch host result
        synchronized (BATCH_RESULT_LOCK) {
            Batch batch = findBath(batchId);
            Map<Long, Boolean> hostResult = Maps.newHashMap(batch.getHostResult());
            hostResult.put(host.getId(), !stdLogger.hasError());
            saveBatch(batch);
        }
    }

    private void runBashScript(SSH2 ssh2, String bashScript, CustomStdLogger stdLogger) throws UnsupportedEncodingException {
        @Cleanup ByteArrayInputStream is
                = new ByteArrayInputStream(bashScript.getBytes(DEFAULT_CHARSET));
        ssh2.scp(is, INIT_SCRIPT, USER_HOME);
        //init script
        ssh2.execCommand(COMMAND_PREFIX + USER_HOME + SEPARATOR + INIT_SCRIPT, stdLogger);
    }

    private SSH2 connectToHost(Host host) {
        //connect
        SSH2 ssh2 = SSH2.connect(host.getAddress(), host.getSshPort());
        String privateKey = host.getPrivateKey();
        String user = host.getUser();
        String password = host.getPassword();
        //authenticate
        if (StringUtils.isNotEmpty(privateKey)) {
            ssh2.authWithPublicKey(user, password, privateKey.toCharArray());
        } else {
            ssh2.auth(user, password);
        }
        return ssh2;
    }

    private Batch findBath(Long batchId) {
        return batchRepository.findOne(batchId);
    }

    private String getBatchLogfile(String homePath, Long batchId, Long hostId) {
        return homePath + SEPARATOR + "logs" + SEPARATOR + batchId + SEPARATOR + hostId + ".log";
    }

    private void copyScriptFiles(Long scriptId, SSH2 ssh2) {
        List<ScriptFile> files = findScriptFiles(scriptId);
        //do scp, if file exist skip
        for (ScriptFile sf : files) {
            GlobalFile gf = sf.getGlobalFile();
            File nativeFile = fileStoreService.lookup(gf.getId());
            ssh2.scp(nativeFile, gf.getName(), sf.getScpPath());
        }
    }

    private Batch createBatch(List<Long> scriptIds, List<Long> hostIds) {
        Batch batch = new Batch();
        batch.setScriptIds(scriptIds);
        batch.setHostIds(hostIds);
        return saveBatch(batch);
    }

    private Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

}