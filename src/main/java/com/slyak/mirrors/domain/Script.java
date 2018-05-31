package com.slyak.mirrors.domain;

import com.google.common.collect.Lists;
import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/10
 * @since 1.3.0
 */
@Entity
@Table(name = "t_script")
@Data
public class Script extends AbstractPersistable<Long> {

    private String name;

    @Lob
    private String help;

    @Lob
    private String content = "#!/bin/sh";

    private String osName;

    @Type(type = JSONType.TYPE)
    @Column(length = 3000)
    private List<ScriptEnv> envs = Lists.newArrayList();

    @Type(type = JSONType.TYPE)
    @Column(length = 1000)
    private List<String> osVersions;

    private ScriptStatus latestStatus = ScriptStatus.UNKNOWN;

    @Override
    public String toString() {
        return name;
    }
}