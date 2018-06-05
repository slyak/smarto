package com.slyak.smarto.domain;

import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/11
 * @since 1.3.0
 */
@Entity
@Table(name = "t_os", uniqueConstraints = @UniqueConstraint(columnNames = "os"))
@Data
public class OS extends AbstractPersistable<Long> {

    private String os;

    @Type(type = JSONType.TYPE)
    private List<String> versions;
}
