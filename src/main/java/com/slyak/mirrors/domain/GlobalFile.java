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
@Table(name = "t_mfile")
@Data
public class GlobalFile extends AbstractPersistable<Long> {
    private String name;
    private String path;
    private String version;
    private long size;
}
