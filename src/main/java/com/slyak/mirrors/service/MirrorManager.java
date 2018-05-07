package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.HostGroup;
import com.slyak.mirrors.domain.ProjectHost;
import com.slyak.mirrors.domain.HostGroupScript;
import com.slyak.mirrors.domain.Project;
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
}
