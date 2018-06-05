package com.slyak.smarto.repository;

import com.slyak.smarto.domain.ScriptFile;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
public interface ScriptFileRepository extends GenericJpaRepository<ScriptFile, Long> {

    List<ScriptFile> findByScriptId(Long scriptId);

    @Modifying
    void deleteByScriptId(Long id);
}