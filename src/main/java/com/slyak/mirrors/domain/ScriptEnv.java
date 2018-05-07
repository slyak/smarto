package com.slyak.mirrors.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/5/7
 * @since 1.3.0
 */
@Entity
@Table(name = "t_script_env")
@Data
public class ScriptEnv extends AbstractPersistable<Long> {

    private Long scriptId;

    private String key;

    private String description;

    private String defValue;
}
