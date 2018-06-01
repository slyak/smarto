package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.ProjectGroup;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface ProjectGroupRepository extends GenericJpaRepository<ProjectGroup, Long> {
    List<ProjectGroup> findByProjectIdOrderByOrderAsc(Long projectId);
}