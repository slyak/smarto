package com.slyak.mirrors.domain;

import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Map;

/**
 * .
 *
 * @author stormning 2018/5/29
 * @since 1.3.0
 */
@Entity
@Table(name = "t_project_group_script")
@Data
public class ProjectGroupScript extends AbstractPersistable<Long> {

    private Long projectGroupId;

    private Long scriptId;

    @Type(type = JSONType.TYPE)
    private Map<String, String> envs;

    @Column(name = "order0")
    private int order = 0;


    @Transient
    private Script script;
}
