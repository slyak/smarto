package com.slyak.mirrors.service;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/23
 * @since 1.3.0
 */
public interface ScriptContext {
    void exec(List<String> scriptFiles);
}
