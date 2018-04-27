package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.Project;
import com.slyak.spring.jpa.GenericJpaRepository;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
public interface ScriptRepository extends GenericJpaRepository<Project, Long> {
}
