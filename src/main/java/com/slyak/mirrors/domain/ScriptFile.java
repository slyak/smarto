package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    private Long scriptId;

    private String globalFileId;

    private String scpPath;

    private String description;

    @Transient
    private GlobalFile globalFile;

}
