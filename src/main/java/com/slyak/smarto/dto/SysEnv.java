package com.slyak.smarto.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/5/25
 * @since 1.3.0
 */
@Data
public class SysEnv implements Serializable {

    private String name;

    private String description;

    private String structure;

}
