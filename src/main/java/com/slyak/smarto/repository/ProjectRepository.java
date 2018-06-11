package com.slyak.smarto.repository;

import com.slyak.smarto.domain.Project;
import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.Status;
import com.slyak.spring.jpa.TemplateQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface ProjectRepository extends GenericJpaRepository<Project, Long> {
    @TemplateQuery
    List<Project> findProjectsHavingScript(@Param("scriptId") Long scriptId);

    Page<Project> findByStatusEquals(Status status, Pageable pageable);
}
