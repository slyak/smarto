package com.slyak.smarto.repository;

import com.slyak.smarto.domain.GlobalFile;
import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/4/17
 * @since 1.3.0
 */
public interface GlobalFileRepository extends GenericJpaRepository<GlobalFile, String> {
    GlobalFile findByMd5(String md5);

    @TemplateQuery
    List<GlobalFile> findUnusedFiles();
}