package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.Project;
import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface ProjectRepository extends GenericJpaRepository<Project, Long> {
    @TemplateQuery
    List<Project> findProjectsHavingScript(@Param("scriptId") Long scriptId);
}
