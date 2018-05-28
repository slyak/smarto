package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.*;
import com.slyak.mirrors.dto.BatchQuery;
import com.slyak.mirrors.dto.SysEnv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface MirrorManager {
    Page<Project> queryProjects(Pageable pageable);

    ProjectRole findProjectRole(Long roleId);

    List<ScriptFile> findScriptFiles(Long scriptId);

    void saveScript(Script script);

    Page<Script> queryScripts(String keyword, Pageable pageable);

    void saveOs(OS os);

    void saveScriptFile(ScriptFile scriptFile);

    List<OS> queryOss();

    OS findOs(String osName);

    Batch execScript(Long scriptId);

    Batch execScripts(Long bizId, List<Long> scriptIds, List<Long> hostIds);

    Global findGlobal();

    Global saveGlobal(Global global);

    Host getTestHost();

    boolean validateHost(Host host);

    String getBatchLogfile(Long batchId, Long hostId);

    Page<Batch> queryBatches(BatchQuery batchQuery, Pageable pageable);

    Page<Host> queryHosts(Pageable pageable);

    void saveHost(Host host);

    boolean validateHost(Host testHost, String command, String contains);

    Batch findBatch(Long batchId);

    List<SysEnv> querySysEnvs();

    Project saveProject(Project project);

    List<ProjectRole> findProjectRoles(Long projectId);

    ProjectRole saveProjectRole(ProjectRole projectRole);

    List<Host> findHostsNotInProjectRole(Long roleId);
}
