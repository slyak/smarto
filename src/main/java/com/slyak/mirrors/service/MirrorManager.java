package com.slyak.mirrors.service;

import com.slyak.core.util.StdCallback;
import com.slyak.mirrors.domain.Group;
import com.slyak.mirrors.domain.GroupHost;
import com.slyak.mirrors.domain.GroupScript;
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

    List<GroupScript> getGroupScripts(Long groupId);

    Group findGroup(Long groupId);

    List<GroupHost> findGroupHosts(Long groupId);

    void execGroupScripts(Long groupId, GroupScriptCallback callback);
}
