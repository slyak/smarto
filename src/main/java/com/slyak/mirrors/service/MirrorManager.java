package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.*;
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

    List<HostGroupScript> getGroupScripts(Long groupId);

    HostGroup findGroup(Long groupId);

    List<ProjectHost> findGroupHosts(Long groupId);

    void execGroupScripts(Long groupId, GroupScriptCallback callback);

    List<ScriptFile> findScriptFiles(Long scriptId);

    List<ScriptEnv> findScriptEnvs(Long scriptId);

    void saveScript(Script script);

    Page<Script> queryScripts(String keyword, Pageable pageable);

    void saveOs(OS os);

    void saveScriptFile(ScriptFile scriptFile);

    List<OS> queryOss();

    OS findOs(String osName);
}
