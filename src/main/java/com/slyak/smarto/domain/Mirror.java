package com.slyak.smarto.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

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

    private Long hostId;

    private String storagePath;

    private String repoFileName;

    private String repoTemplate;

}
