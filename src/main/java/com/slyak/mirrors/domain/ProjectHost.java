package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Entity
@Table(name = "t_project_host")
@Data
public class ProjectHost extends AbstractPersistable<Long> {
    private Long projectId;

    private String host;

    private int sshPort = 22;

    private String user;

    private String password;
}
