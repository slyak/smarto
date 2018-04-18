package com.slyak.mirrors.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * machine group.
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_group")
@Data
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int projectId;

    @Column(length = 3000)
    private String script;
}
