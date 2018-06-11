package com.slyak.smarto.dto;

import com.slyak.spring.jpa.TemplateQueryObject;
import lombok.Data;

/**
 * .
 *
 * @author stormning 2018/5/21
 * @since 1.3.0
 */
@TemplateQueryObject
@Data
public class BatchQuery {
    private long batchId;
    private long startAt;
    private long stopAt;
}