package com.slyak.smarto.domain;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Entity
@Table(name = "t_project_group_host")
@Data
public class ProjectGroupHost {

    @EmbeddedId
    private ProjectGroupHostKey id;

    @Transient
    private Host host;
}