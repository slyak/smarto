package com.slyak.smarto.domain;

import lombok.Data;

import javax.persistence.EmbeddedId;
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
@Data
public class BatchTask {

    @EmbeddedId
    private BatchTaskKey id;

    private long startAt = 0;

    private long stopAt = 0;

    private BatchTaskStatus status = BatchTaskStatus.RUNNING;
}