package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.ProjectGroupScript;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/29
 * @since 1.3.0
 */
public interface ProjectGroupScriptRepository extends GenericJpaRepository<ProjectGroupScript, Long> {
    List<ProjectGroupScript> findByProjectGroupIdOrderByOrderAsc(Long id);

    @Modifying
    void deleteByProjectGroupId(Long id);
}