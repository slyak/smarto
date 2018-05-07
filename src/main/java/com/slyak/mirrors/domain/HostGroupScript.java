package com.slyak.mirrors.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * group file.
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_group_file")
@Data
public class HostGroupScript extends HostGroupScriptKey{

    @EmbeddedId
    private HostGroupScriptKey id;

    @Column(name = "order0")
    private int order;

    @Transient
    private ScriptFile scriptFile;
}
