package com.slyak.mirrors.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table
@Data
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int parentId;
}