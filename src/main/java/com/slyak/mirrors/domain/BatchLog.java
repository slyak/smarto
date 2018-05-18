package com.slyak.mirrors.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/5/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_batch_log")
public class BatchLog extends AbstractPersistable<Long> {

    private Long batchId;

    private Long scriptId;

    private Long hostId;
}