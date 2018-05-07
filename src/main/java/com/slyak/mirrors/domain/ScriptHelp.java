package com.slyak.mirrors.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * .
 *
 * @author stormning 2018/5/7
 * @since 1.3.0
 */
@Entity
@Table(name = "t_script_help")
@Data
public class ScriptHelp {

    @Id
    private Long scriptId;

    @Lob
    private String content;
}
