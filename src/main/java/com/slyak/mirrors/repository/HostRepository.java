package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.Host;
import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/17
 * @since 1.3.0
 */
public interface HostRepository extends GenericJpaRepository<Host, Long> {
    List<Host> findByTestHostTrue();

    @TemplateQuery
    List<Host> findHostNotInProjectRole(Long roleId);
}