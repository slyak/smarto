package com.slyak.mirrors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/5/16
 * @since 1.3.0
 */
@Embeddable
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchTaskKey implements Serializable {

    private Long batchId;

    private Long hostId;
}