package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.Host;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/17
 * @since 1.3.0
 */
public interface HostRepository extends GenericJpaRepository<Host, Long> {
    List<Host> findByTestHostTrue();
}