package com.slyak.smarto.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/6/7
 * @since 1.3.0
 */
@Data
@AllArgsConstructor
public class Executable implements Serializable{

    private Long scriptId;

    private String file;

    private String realName;

}
