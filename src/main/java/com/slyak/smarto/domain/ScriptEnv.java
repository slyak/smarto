package com.slyak.smarto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * .
 *
 * @author stormning 2018/5/7
 * @since 1.3.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptEnv implements Serializable {

    private String key;

    private String description;

    private String defValue;

}
