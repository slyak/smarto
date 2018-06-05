package com.slyak.smarto.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/6/1
 * @since 1.3.0
 */
@Data
public class ScriptInstances {

    private List<Long> ids;

    private Map<Long, Map<String, String>> envs;

}
