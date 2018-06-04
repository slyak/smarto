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
import com.slyak.mirrors.dto.ScriptInstances;
import com.slyak.mirrors.dto.SysEnv;
import com.slyak.mirrors.repository.*;
import com.slyak.web.support.freemarker.FreemarkerTemplateRender;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
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
public class MirrorManagerImpl implements MirrorManager, ApplicationEventPublisherAware, ApplicationContextAware, InitializingBean {

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

    private Map<BatchOwner, BatchProvider> batchProviders = Maps.newHashMap();

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
            ProjectGroupScriptRepository projectGroupScriptRepository
    ) {
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
    public List<ScriptFile> findScriptFiles(Long scriptId) {
        return scriptFileRepository.findByScriptId(scriptId);
    }

    @Override
    @Transactional
    public void saveScript(Script script) {
        scriptRepository.save(script);
    }

    @Override
    public Page<Script> queryScripts(String keyword, Pageable pageable) {
        return scriptRepository.queryByNameLike(StringUtils.isEmpty(keyword) ? null : "%" + keyword.toLowerCase() + "%", pageable);
    }

    @Override
    @Transactional
    public void saveOs(OS os) {
        osRepository.save(os);
    }

    @Override
    @Transactional
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

    private Batch createBatch(BatchOwner owner, Long ownerId) {
        BatchProvider batchProvider = batchProviders.get(owner);
        //get hosts
        List<Long> hostIds = batchProvider.getOwnerHostIds(ownerId);
        ScriptInstances instances = batchProvider.getScriptsInstances(ownerId);
        if (CollectionUtils.isEmpty(hostIds) || CollectionUtils.isEmpty(instances.getIds())) {
            return null;
        }

        Batch batch = new Batch();
        batch.setOwner(owner);
        batch.setOwnerId(ownerId);
        batch.setScriptIds(instances.getIds());
        batch.setScriptEnvs(instances.getEnvs());
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

    @Override
    @Transactional
    public Batch execOwnerScripts(BatchOwner owner, Long ownerId) {
        Batch batch = createBatch(owner, ownerId);
        if (batch != null) {
            runBatch(batch.getId());
        }
        return batch;
    }

    @Override
    public Global findGlobal() {
        Global global = globalRepository.findOne(Global.ONLY_ID);
        return global == null ? saveGlobal(new Global()) : global;
    }

    @Override
    @SneakyThrows
    @Transactional
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
    private void runBatch(Long batchId) {
        Batch batch = batchRepository.findOne(batchId);
        taskExecutor.execute(() -> {
            List<Host> hosts = hostRepository.findAll(batch.getHostIds());
            List<Script> scripts = scriptRepository.findAll(batch.getScriptIds());
            for (Host host : hosts) {
                //run command in one host
                String logFile = getBatchLogfile(batchId, host.getId());
                asyncConnectExecute(batch, host, scripts, logFile);
            }
        });

    }

    private void asyncConnectExecute(Batch batch, Host host, List<Script> scripts, String logfile) {
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
                            stdEvent.setProperty("batchId", batch.getId());
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
                        setupSysEnvs(model, batch, host);
                        Optional.of(batch.getScriptEnvs()).ifPresent(scriptEnvs -> model.putAll(scriptEnvs.get(script.getId())));
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
            taskKey.setBatchId(batch.getId());
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

    private void setupSysEnvs(Map<String, Object> model, Batch batch, Host host) {
        Collection<SysEnvProvider> sysEnvProviders = getSysEnvProviders();
        if (!CollectionUtils.isEmpty(sysEnvProviders)) {
            for (SysEnvProvider provider : sysEnvProviders) {
                SysEnv metadata = provider.getMetadata();
                model.put(metadata.getName(), provider.provide(batch, host));
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
    public List<SysEnv> querySysEnvs() {
        List<SysEnv> sysEnvs = Lists.newArrayList();
        for (SysEnvProvider provider : getSysEnvProviders()) {
            sysEnvs.add(provider.getMetadata());
        }
        return sysEnvs;
    }

    @Override
    @Transactional
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<ProjectGroup> findProjectGroups(Long projectId) {
        return projectGroupRepository.findByProjectIdOrderByOrderAsc(projectId);
    }

    @Override
    @Transactional
    public ProjectGroup saveProjectGroup(ProjectGroup projectGroup) {
        return projectGroupRepository.save(projectGroup);
    }

    @Override
    public List<Host> findHostsNotInProjectGroup(Long groupId) {
        return hostRepository.findHostNotInProjectGroup(groupId);
    }

    @Override
    @Transactional
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
    public List<ProjectGroupHost> findProjectGroupHosts(Long groupId) {
        return projectGroupHostRepository.findByIdProjectGroupId(groupId);
    }

    @Override
    public List<ProjectGroupScript> findProjectGroupScripts(Long groupId) {
        return projectGroupScriptRepository.findByProjectGroupIdOrderByOrderAsc(groupId);
    }

    @Override
    @Transactional
    public void addGroupScripts(Long groupId, List<Long> scriptIds) {
        for (Long scriptId : scriptIds) {
            ProjectGroupScript groupScript = new ProjectGroupScript();
            groupScript.setProjectGroupId(groupId);
            groupScript.setScriptId(scriptId);
            projectGroupScriptRepository.save(groupScript);
        }
    }

    @Override
    @Transactional
    public void deleteProjectGroupScript(Long id) {
        projectGroupScriptRepository.delete(id);
    }

    @Override
    @Transactional
    public void saveGroupScript(ProjectGroupScript groupScript) {
        projectGroupScriptRepository.save(groupScript);
    }

    @Override
    @Transactional
    public void updateGroupOrders(List<Long> groupIds) {
        for (int i = 0; i < groupIds.size(); i++) {
            ProjectGroup group = projectGroupRepository.findOne(groupIds.get(i));
            group.setOrder(i);
            projectGroupRepository.save(group);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteScript(Long id) {
        scriptFileRepository.deleteByScriptId(id);
        scriptRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteScriptFile(ScriptFile scriptFile) {
        GlobalFile globalFile = scriptFile.getGlobalFile();
        //TODO reuse global file
        fileStoreService.removeFile(globalFile.getId());
        scriptFileRepository.delete(scriptFile);
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
    @Transactional
    public void saveHost(Host host) {
        hostRepository.save(host);
    }

    private String getBatchLogDirectory() {
        return PROJECT_HOME + SEPARATOR + "logs";
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

    @Override
    public void afterPropertiesSet() throws Exception {
        this.batchProviders.put(BatchOwner.PROJECT_GROUP, new ProjectGroupBatchProvider());
        this.batchProviders.put(BatchOwner.SCRIPT, new ScriptBatchProvider());
    }

    public abstract class AbstractBatchProvider<T> implements BatchProvider {
        @Override
        public ScriptInstances getScriptsInstances(Long ownerId) {
            ScriptInstances instances = new ScriptInstances();
            T prepared = prepare(ownerId);
            List<Long> ids = Lists.newArrayList();
            Map<Long, Map<String, String>> envs = Maps.newHashMap();
            if (prepared != null) {
                decorateIds(prepared, ids);
                decorateEnvs(prepared, envs);
            }
            instances.setIds(ids);
            instances.setEnvs(envs);
            return instances;
        }

        abstract void decorateEnvs(T prepared, Map<Long, Map<String, String>> envs);

        abstract void decorateIds(T prepared, List<Long> ids);

        abstract T prepare(Long ownerId);
    }

    public class ScriptBatchProvider extends AbstractBatchProvider<Script> {
        @Override
        public List<Long> getOwnerHostIds(Long scriptId) {
            Host testHost = getTestHost();
            if (testHost != null) {
                return Collections.singletonList(testHost.getId());
            }
            return Collections.emptyList();
        }

        @Override
        void decorateEnvs(Script prepared, Map<Long, Map<String, String>> envs) {
            Map<String, String> ses = Maps.newHashMap();
            for (ScriptEnv se : prepared.getEnvs()) {
                ses.put(se.getKey(), se.getDefValue());
            }
            envs.put(prepared.getId(), ses);
        }

        @Override
        void decorateIds(Script prepared, List<Long> ids) {
            ids.add(prepared.getId());
        }

        @Override
        Script prepare(Long ownerId) {
            return scriptRepository.findOne(ownerId);
        }
    }

    public class ProjectGroupBatchProvider extends AbstractBatchProvider<List<ProjectGroupScript>> {

        @Override
        public List<Long> getOwnerHostIds(Long groupId) {
            List<ProjectGroupHost> hosts = findProjectGroupHosts(groupId);
            return hosts.stream().map(projectGroupHost -> projectGroupHost.getHost().getId()).collect(Collectors.toList());
        }

        @Override
        void decorateEnvs(List<ProjectGroupScript> prepared, Map<Long, Map<String, String>> envs) {
            for (ProjectGroupScript pgs : prepared) {
                Script script = pgs.getScript();
                List<ScriptEnv> ses = script.getEnvs();
                Map<String, String> finalEnvs = Maps.newHashMap();
                if (!CollectionUtils.isEmpty(ses)) {
                    Map<String, String> pgsEnvs = pgs.getEnvs();
                    for (ScriptEnv se : ses) {
                        String key = se.getKey();
                        finalEnvs.put(key, getEnv(key, se.getDefValue(), pgsEnvs));
                    }
                }
                envs.put(pgs.getScriptId(), finalEnvs);
            }
        }

        private String getEnv(String key, String defValue, Map<String, String> pgsEnvs) {
            if (pgsEnvs == null) {
                return defValue;
            }
            String val = pgsEnvs.get(key);
            return StringUtils.isBlank(val) ? defValue : val;
        }

        @Override
        void decorateIds(List<ProjectGroupScript> prepared, List<Long> ids) {
            for (ProjectGroupScript script : prepared) {
                ids.add(script.getScriptId());
            }
        }

        @Override
        List<ProjectGroupScript> prepare(Long ownerId) {
            return findProjectGroupScripts(ownerId);
        }
    }
}