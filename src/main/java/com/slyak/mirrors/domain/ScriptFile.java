package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * group file.
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_script_file")
@Data
public class ScriptFile extends AbstractPersistable<Long> {

    private String name;

    private String localPath;

    private String scpPath;
}
