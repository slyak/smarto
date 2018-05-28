package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * machine group.
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_project_role")
@Data
public class ProjectRole extends AbstractPersistable<Long> {

    private Long projectId;

    private String name;

    private String description;

    @Column(name = "order0")
    private int order = 0;

    @Transient
    private Project project;

    @Transient
    private List<Host> hosts;
}
