package com.slyak.mirrors.domain;

import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * group file.
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
@Entity
@Table(name = "t_group_file")
@Data
public class GroupFile extends AbstractPersistable<Long> {

    private FileType fileType = FileType.NORMAL;

    private String typeValue;

    @Type(type = JSONType.TYPE)
    private List<Long> groupFileIds;
}
