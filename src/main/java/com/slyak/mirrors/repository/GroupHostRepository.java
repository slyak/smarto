package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.GroupHost;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface GroupHostRepository extends GenericJpaRepository<GroupHost, Long> {
    List<GroupHost> findByGroupId(Long groupId);
}