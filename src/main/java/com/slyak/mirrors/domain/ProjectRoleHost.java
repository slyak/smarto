package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Entity
@Table(name = "t_project_role_host", uniqueConstraints = @UniqueConstraint(columnNames = {"project_role_id", "host_id"}))
@Data
public class ProjectRoleHost extends AbstractPersistable<Long> {
    private Long projectRoleId;

    private Long hostId;
}