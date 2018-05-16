package com.slyak.mirrors.domain;

import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_batch")
@Data
public class Batch extends AbstractPersistable<Long> {

    @Type(type = JSONType.TYPE)
    private List<Long> scriptIds;

    private String description;

    private long createAt = System.currentTimeMillis();
}
