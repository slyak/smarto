package com.slyak.mirrors.repository;

import com.slyak.mirrors.domain.Script;
import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * .
 *
 * @author stormning 2018/5/10
 * @since 1.3.0
 */
public interface ScriptRepository extends GenericJpaRepository<Script, Long> {
    @TemplateQuery
    Page<Script> queryByNameLike(@Param("keyword") String keyword, Pageable pageable);
}
