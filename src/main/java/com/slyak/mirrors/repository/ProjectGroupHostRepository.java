package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.ProjectGroupHost;
import com.slyak.mirrors.domain.ProjectGroupHostKey;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/28
 * @since 1.3.0
 */
public interface ProjectGroupHostRepository extends GenericJpaRepository<ProjectGroupHost, ProjectGroupHostKey> {
    List<ProjectGroupHost> findByIdProjectGroupId(Long id);
}