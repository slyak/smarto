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
@Table(name = "t_group_host")
@Data
public class GroupHost extends AbstractPersistable<Long> {
    private Long groupId;

    private String host;

    private int sshPort = 22;

    private String user;

    private String password;
}
