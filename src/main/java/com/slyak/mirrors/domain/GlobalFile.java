package com.slyak.mirrors.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
@Entity
@Table(name = "t_mfile")
@Data
public class GlobalFile {
    @Id
    private String id;
    private String name;
    private long size;
}
