package com.slyak.mirrors.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Data
public class ScriptMessage implements Serializable {
    private String user;
    private Long groupId;
}
