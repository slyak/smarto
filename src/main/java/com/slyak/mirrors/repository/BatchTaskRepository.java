package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.BatchTask;
import com.slyak.mirrors.domain.BatchTaskKey;
import com.slyak.spring.jpa.GenericJpaRepository;

/**
 * .
 *
 * @author stormning 2018/5/21
 * @since 1.3.0
 */
public interface BatchTaskRepository extends GenericJpaRepository<BatchTask, BatchTaskKey> {
}
