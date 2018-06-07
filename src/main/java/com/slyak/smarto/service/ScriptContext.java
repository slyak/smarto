package com.slyak.smarto.service;

import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/5/23
 * @since 1.3.0
 */
public interface ScriptContext {
    Map<Long, Boolean> exec(List<Executable> scriptFiles);
}
