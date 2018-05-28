package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.ProjectRoleHost;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/28
 * @since 1.3.0
 */
public interface ProjectRoleHostRepository extends GenericJpaRepository<ProjectRoleHost, Long> {
    List<ProjectRoleHost> findByRoleId(Long id);
}
