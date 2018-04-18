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
@Table(name = "t_global")
@Data
public class Global {

    @Id
    @GeneratedValue
    private Long id;

    private String storePath;
}
