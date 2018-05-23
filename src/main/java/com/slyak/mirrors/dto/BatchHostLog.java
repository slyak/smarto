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
public class BatchHostLog implements Serializable {

    private Long batchId;

    private Long hostId;


    public String getLogKey() {
        return batchId + "_" + hostId;
    }
}
