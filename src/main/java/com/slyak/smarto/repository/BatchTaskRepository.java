package com.slyak.smarto.repository;

import com.slyak.smarto.domain.BatchTask;
import com.slyak.smarto.domain.BatchTaskKey;
import com.slyak.spring.jpa.GenericJpaRepository;

/**
 * .
 *
 * @author stormning 2018/5/21
 * @since 1.3.0
 */
public interface BatchTaskRepository extends GenericJpaRepository<BatchTask, BatchTaskKey> {
}
