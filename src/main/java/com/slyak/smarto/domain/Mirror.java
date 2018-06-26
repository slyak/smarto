package com.slyak.smarto.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * .
 *
 * @author stormning 2018/6/5
 * @since 1.3.0
 */
@Entity
@Table(name = "t_mirror")
@Data
public class Mirror extends AbstractPersistable<Long> {

    private String name;

    private String baseUrl;

    @Column(length = 500)
    private String rootPath;

    private Long hostId;

    @Column(length = 3000)
    private String script = "#!/bin/bash";

    @Column(length = 3000)
    private String help;

    private long lastUpdate;

    @Transient
    private Host host;

}
