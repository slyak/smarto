package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface MirrorManager {
    Page<Project> queryProjects(Pageable pageable);
}
