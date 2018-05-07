package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.ScriptEnv;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/7
 * @since 1.3.0
 */
public interface ScriptEnvRepository extends GenericJpaRepository<ScriptEnv, Long> {
    List<ScriptEnv> findByScriptId(Long scriptId);
}