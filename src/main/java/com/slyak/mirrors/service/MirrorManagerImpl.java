package com.slyak.mirrors.service;

import ch.qos.logback.classic.Logger;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.slyak.core.ssh2.SSH2;
import com.slyak.core.ssh2.StdCallback;
import com.slyak.core.ssh2.StdEvent;
import com.slyak.core.ssh2.StdEventLogger;
import com.slyak.core.util.LoggerUtils;
import com.slyak.core.util.StringUtils;
import com.slyak.file.FileStoreService;
import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.dto.BatchQuery;
import com.slyak.mirrors.dto.SysEnv;
import com.slyak.mirrors.repository.*;
import com.slyak.web.support.freemarker.FreemarkerTemplateRender;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
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
public class MirrorManagerImpl implements MirrorManager, ApplicationEventPublisherAware, ApplicationContextAware {

    private static final String ENV_PATTERN = "\\$[{]?([A-Za-z0-9_-]+)[}]?";

    private static final String SH = ".sh";

    private static final char SEPARATOR = '/';

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String PROJECT_HOME = SystemUtils.getUserHome().getPath() + SEPARATOR + ".itasm";

    private final ProjectRepository projectRepository;

    private final ScriptFileRepository scriptFileRepository;

    private final ProjectGroupHostRepository projectGroupHostRepository;

    private final ProjectGroupScriptRepository projectGroupScriptRepository;

    private final ProjectGroupRepository projectGroupRepository;

    private final ScriptRepository scriptRepository;

    private final OSRepository osRepository;

    private final BatchRepository batchRepository;

    private final HostRepository hostRepository;

    private final GlobalRepository globalRepository;

    private final FileStoreService<String> fileStoreService;

    private final BatchTaskRepository batchTaskRepository;

    private final TaskExecutor taskExecutor;

    private final FreemarkerTemplateRender templateRender;

    private ApplicationEventPublisher eventPublisher;

    private ApplicationContext appContext;

    @Autowired
    public MirrorManagerImpl(
            ProjectRepository projectRepository,
            ScriptFileRepository scriptFileRepository,
            ProjectGroupRepository projectGroupRepository,
            ScriptRepository scriptRepository,
            OSRepository osRepository,
            BatchRepository batchRepository,
            HostRepository hostRepository,
            GlobalRepository globalRepository,
            FileStoreService<String> fileStoreService,
            BatchTaskRepository batchTaskRepository,
            TaskExecutor taskExecutor,
            FreemarkerTemplateRender templateRender,
            ProjectGroupHostRepository projectGroupHostRepository,
            ProjectGroupScriptRepository projectGroupScriptRepository) {
        this.projectRepository = projectRepository;
        this.scriptFileRepository = scriptFileRepository;
        this.projectGroupRepository = projectGroupRepository;
        this.scriptRepository = scriptRepository;
        this.osRepository = osRepository;
        this.batchRepository = batchRepository;
        this.hostRepository = hostRepository;
        this.globalRepository = globalRepository;
        this.fileStoreService = fileStoreService;
        this.batchTaskRepository = batchTaskRepository;
        this.taskExecutor = taskExecutor;
        this.templateRender = templateRender;
        this.projectGroupHostRepository = projectGroupHostRepository;
        this.projectGroupScriptRepository = projectGroupScriptRepository;
    }

    @Override
    public Page<Project> queryProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public ProjectGroup findProjectGroup(Long groupId) {
        return null;
    }

    @Override
    public List<ScriptFile> findScriptFiles(Long scriptId) {
        return scriptFileRepository.findByScriptId(scriptId);
    }

    @Override
    public void saveScript(Script script) {
        scriptRepository.save(script);
    }

    /**
     * @param script
     * @Deprecated
     */
    private void generateScriptEnvs(Script script) {
        String content = script.getContent();
        Set<String> envs = content == null ?
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
        return scriptRepository.queryByNameLike(StringUtils.isEmpty(keyword) ? null : "%" + keyword.toLowerCase() + "%", pageable);
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
    public Batch execScript(Long scriptId) {
        Host host = getTestHost();
        return execScripts(BatchOwner.SCRIPT, scriptId, Collections.singletonList(scriptId), Collections.singletonList(host.getId()));
    }

    @Override
    public Batch execScripts(BatchOwner owner, Long ownerId, List<Long> scriptIds, List<Long> hostIds) {
        Batch batch = createBatch(owner, ownerId, scriptIds, hostIds);
        runBatch(batch);
        return batch;
    }

    @Override
    public Global findGlobal() {
        Global global = globalRepository.findOne(Global.ONLY_ID);
        return global == null ? saveGlobal(new Global()) : global;
    }

    @Override
    @SneakyThrows
    public Global saveGlobal(Global global) {
        return globalRepository.save(global);
    }

    @Override
    public Host getTestHost() {
        List<Host> hosts = hostRepository.findByTestHostTrue();
        if (CollectionUtils.isEmpty(hosts)) {
            return null;
        }
        return hosts.get((int) (Math.random() * hosts.size()));
    }

    @SneakyThrows
    private void runBatch(Batch batch) {
        taskExecutor.execute(() -> {
            List<Host> hosts = hostRepository.findAll(batch.getHostIds());
            List<Script> scripts = scriptRepository.findAll(batch.getScriptIds());
            Long batchId = batch.getId();
            for (Host host : hosts) {
                //run command in one host
                String logFile = getBatchLogfile(batchId, host.getId());
                asyncConnectExecute(batchId, host, scripts, logFile);
            }
        });

    }

    private void asyncConnectExecute(Long batchId, Host host, List<Script> scripts, String logfile) {
        taskExecutor.execute(() -> {
            //connect and auth
            SSH2 ssh2 = null;
            //custom std logger
            StdEventLogger stdLogger = null;
            try {
                ssh2 = connectToHost(host);
                if (ssh2 != null && ssh2.isAuthSuccess()) {
                    Logger logger = LoggerUtils.createLogger(logfile, "%msg%n");
                    stdLogger = new StdEventLogger(logger, eventPublisher) {
                        @Override
                        protected StdEvent decorate(StdEvent stdEvent) {
                            stdEvent.setProperty("batchId", batchId);
                            stdEvent.setProperty("hostId", host.getId());
                            return stdEvent;
                        }
                    };
                    Set<String> fileScpPaths = Sets.newHashSet();
                    List<String> scriptFiles = Lists.newArrayList();
                    for (Script script : scripts) {
                        //copy script files
                        List<ScriptFile> files = findScriptFiles(script.getId());
                        //do scp, if file exist skip
                        for (ScriptFile sf : files) {
                            GlobalFile gf = sf.getGlobalFile();
                            File nativeFile = fileStoreService.lookup(gf.getId());
                            String fileName = gf.getName();
                            String scpPath = sf.getScpPath();
                            ssh2.scp(nativeFile, fileName, scpPath);
                            logger.info("Scp file {} to host path {}:{}", fileName, host.getIp(), scpPath);
                            fileScpPaths.add(scpPath);
                        }

                        //run bash script
                        fileScpPaths.add(PROJECT_HOME);
                        Map<String, Object> model = Maps.newHashMap();
                        setupSysEnvs(model, batchId, host);
                        model.putAll(getBatchScriptEnvs(batchId, script));
                        //\r\n will cause dos file format and will cause file not found exception
                        String bashScript = StringUtils.replace(templateRender.renderStringTpl(script.getContent(), model), "\r\n", "\n");
                        @Cleanup ByteArrayInputStream is = new ByteArrayInputStream(bashScript.getBytes(DEFAULT_CHARSET));
                        String scriptName = script.getId() + SH;
                        ssh2.scp(is, scriptName, PROJECT_HOME);
                        scriptFiles.add(PROJECT_HOME + SEPARATOR + scriptName);
                    }

                    ScriptContext context = ScriptContexts.select(host, ssh2, stdLogger, fileScpPaths);
                    context.exec(scriptFiles);
                }
            } catch (Exception e) {
                log.error("An error occurred : {}", e);
            } finally {
                if (ssh2 != null) {
                    ssh2.disconnect();
                }
            }
            //log batch host result
            BatchTaskKey taskKey = new BatchTaskKey();
            taskKey.setBatchId(batchId);
            taskKey.setHostId(host.getId());
            BatchTask batchTask = batchTaskRepository.findOne(taskKey);
            batchTask.setStopAt(System.currentTimeMillis());
            BatchTaskStatus status = stdLogger == null ?
                    BatchTaskStatus.FAILED :
                    (stdLogger.hasError() ? BatchTaskStatus.FAILED : BatchTaskStatus.SUCCESS);
            batchTask.setStatus(status);

            batchTaskRepository.save(batchTask);
        });
    }

    private Map<String, String> getBatchScriptEnvs(Long batchId, Script script) {
        Map<String, String> envs = Maps.newHashMap();
        Map<String, String> batchEnvs = getBatchScriptEnvs(batchId, script.getId());
        for (ScriptEnv env : script.getEnvs()) {
            String key = env.getKey();
            String current = batchEnvs.get(key);
            envs.put(key, StringUtils.isEmpty(current) ? env.getDefValue() : current);

        }
        return envs;
    }

    //TODO project batch
    private Map<String, String> getBatchScriptEnvs(Long batchId, Long scriptId) {
        return Collections.emptyMap();
    }

    private void setupSysEnvs(Map<String, Object> model, Long batchId, Host host) {
        Collection<SysEnvProvider> sysEnvProviders = getSysEnvProviders();
        if (!CollectionUtils.isEmpty(sysEnvProviders)) {
            for (SysEnvProvider provider : sysEnvProviders) {
                SysEnv metadata = provider.getMetadata();
                model.put(metadata.getName(), provider.provide(batchId, host));
            }
        }
    }

    private Collection<SysEnvProvider> getSysEnvProviders() {
        return appContext.getBeansOfType(SysEnvProvider.class).values();
    }

    private SSH2 connectToHost(Host host) {
        SSH2 ssh2 = null;
        try {
            //connect
            ssh2 = SSH2.connect(host.getIp(), host.getSshPort());
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
        } catch (Exception e) {
            log.info("Connect to host {} failed", host);
        }
        return ssh2;
    }

    @Override
    public boolean validateHost(Host host) {
        return validateHost(host, null, null);
    }

    @Override
    public boolean validateHost(Host host, String command, String contains) {
        SSH2 ssh2 = null;
        boolean result = false;
        try {
            ssh2 = connectToHost(host);
            if (ssh2 != null) {
                result = ssh2.isAuthSuccess();
            }
            if (result && StringUtils.isNotEmpty(command)) {
                final boolean[] cmdContains = {false};
                ssh2.execCommand(command, new StdCallback() {
                    @Override
                    public void processOut(String out) {
                        log.info(out);
                        if (out.contains(contains)) {
                            cmdContains[0] = true;
                        }
                    }

                    @Override
                    public void processError(String error) {
                        log.error(error);
                    }
                });
                return cmdContains[0];
            }

        } catch (Exception e) {
            log.error("An exception occurred when validating host {}", e);
        } finally {
            if (ssh2 != null) {
                ssh2.disconnect();
            }
        }
        return result;
    }

    @Override
    public Batch findBatch(Long batchId) {
        return batchRepository.findOne(batchId);
    }

    @Override
    public List<SysEnv> querySysEnvs() {
        List<SysEnv> sysEnvs = Lists.newArrayList();
        for (SysEnvProvider provider : getSysEnvProviders()) {
            sysEnvs.add(provider.getMetadata());
        }
        return sysEnvs;
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<ProjectGroup> findProjectGroups(Long groupId) {
        return projectGroupRepository.findByProjectIdOrderByOrderAsc(groupId);
    }

    @Override
    public ProjectGroup saveProjectGroup(ProjectGroup projectGroup) {
        return projectGroupRepository.save(projectGroup);
    }

    @Override
    public List<Host> findHostsNotInProjectGroup(Long groupId) {
        return hostRepository.findHostNotInProjectGroup(groupId);
    }

    @Override
    public void addGroupHosts(Long groupId, List<Long> hostIds) {
        for (Long hostId : hostIds) {
            ProjectGroupHost groupHost = new ProjectGroupHost();
            groupHost.setId(new ProjectGroupHostKey(groupId, hostId));
            projectGroupHostRepository.save(groupHost);
        }
    }

    @Override
    public void deleteProjectGroupHost(ProjectGroupHostKey id) {
        projectGroupHostRepository.delete(id);
    }

    @Override
    public List<ProjectGroupHost> findProjectGroupHosts(Long id) {
        return projectGroupHostRepository.findByIdProjectGroupId(id);
    }

    @Override
    public List<ProjectGroupScript> findProjectGroupScripts(Long id) {
        return projectGroupScriptRepository.findByProjectGroupIdOrderByOrderAsc(id);
    }

    @Override
    public void addGroupScripts(Long groupId, List<Long> scriptIds) {
        for (Long scriptId : scriptIds) {
            ProjectGroupScript groupScript = new ProjectGroupScript();
            groupScript.setProjectGroupId(groupId);
            groupScript.setScriptId(scriptId);
            projectGroupScriptRepository.save(groupScript);
        }
    }

    @Override
    public void deleteProjectGroupScript(Long id) {
        projectGroupScriptRepository.delete(id);
    }

    @Override
    public void saveGroupScript(ProjectGroupScript groupScript) {
        projectGroupScriptRepository.save(groupScript);
    }

    @Override
    public void updateGroupOrders(List<Long> groupIds) {
        for (int i = 0; i < groupIds.size(); i++) {
            ProjectGroup group = projectGroupRepository.findOne(groupIds.get(i));
            group.setOrder(i);
            projectGroupRepository.save(group);
        }
    }

    @Override
    public void updateGroupScriptOrders(List<Long> groupScriptIds) {
        for (int i = 0; i < groupScriptIds.size(); i++) {
            ProjectGroupScript script = projectGroupScriptRepository.findOne(groupScriptIds.get(i));
            script.setOrder(i);
            projectGroupScriptRepository.save(script);
        }
    }

    @Override
    @Transactional
    public void deleteProjectGroup(Long id) {
        projectGroupHostRepository.deleteByIdProjectGroupId(id);
        projectGroupScriptRepository.deleteByProjectGroupId(id);
        projectGroupRepository.delete(id);
    }

    @Override
    public List<Project> findProjectsHavingScript(Long id) {
        return projectRepository.findProjectsHavingScript(id);
    }

    @Override
    public void deleteScript(Long id) {
        scriptFileRepository.deleteByScriptId(id);
        scriptRepository.delete(id);
    }

    @Override
    public void deleteScriptFile(ScriptFile scriptFile) {
        GlobalFile globalFile = scriptFile.getGlobalFile();
        //TODO reuse global file
        fileStoreService.removeFile(globalFile.getId());
        scriptFileRepository.delete(scriptFile);
    }

    private boolean isGlobalFileInUse(GlobalFile globalFile) {
        return false;
    }

    @Override
    public String getBatchLogfile(Long batchId, Long hostId) {
        return getBatchLogDirectory() + SEPARATOR + batchId + SEPARATOR + hostId + ".log";
    }

    @Override
    public Page<Batch> queryBatches(BatchQuery batchQuery, Pageable pageable) {
        return batchRepository.findAll(pageable);
    }

    @Override
    public Page<Host> queryHosts(Pageable pageable) {
        return hostRepository.findAll(pageable);
    }

    @Override
    public void saveHost(Host host) {
        hostRepository.save(host);
    }

    private String getBatchLogDirectory() {
        return PROJECT_HOME + SEPARATOR + "logs";
    }

    private Batch createBatch(BatchOwner owner, Long ownerId, List<Long> scriptIds, List<Long> hostIds) {
        Batch batch = new Batch();
        batch.setOwnerId(ownerId);
        batch.setScriptIds(scriptIds);
        batch.setHostIds(hostIds);
        saveBatch(batch);

        Long batchId = batch.getId();
        for (Long hostId : hostIds) {
            BatchTask task = new BatchTask();
            task.setId(new BatchTaskKey(batchId, hostId));
            task.setStartAt(System.currentTimeMillis());
            batchTaskRepository.save(task);
        }

        return batch;

    }

    private Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        this.appContext = appContext;
    }
}