package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.ScriptFile;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
public interface ScriptFileRepository extends GenericJpaRepository<ScriptFile, Long> {

    List<ScriptFile> findByScriptId(Long scriptId);
}