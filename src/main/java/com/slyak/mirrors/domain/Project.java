package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_project")
@Data
public class Project extends AbstractPersistable<Long> {

    private String name;


    private String description;
}