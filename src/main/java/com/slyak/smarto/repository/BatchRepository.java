package com.slyak.smarto.repository;

import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.dto.BatchQuery;
import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * .
 *
 * @author stormning 2018/5/16
 * @since 1.3.0
 */
public interface BatchRepository extends GenericJpaRepository<Batch, Long> {
    @TemplateQuery
    Page<Batch> queryBatches(BatchQuery batchQuery, Pageable pageable);
}
