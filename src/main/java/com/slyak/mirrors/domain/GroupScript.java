package com.slyak.mirrors.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * group file.
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_group_file")
@Data
public class GroupScript {
    @Id
    @GeneratedValue
    private Long id;

    private FileType fileType = FileType.NORMAL;

    private String storePath;
}
