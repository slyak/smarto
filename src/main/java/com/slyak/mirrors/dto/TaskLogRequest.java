package com.slyak.mirrors.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/5/18
 * @since 1.3.0
 */
@Data
public class TaskLogRequest implements Serializable {

    private String id;

    private Long batchId;

    private Long hostId;

    private int line = 0;
}