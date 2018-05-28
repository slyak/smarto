package com.slyak.mirrors.domain;

import com.slyak.spring.jpa.hibernate.JSONType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Map;

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

    @Type(type = JSONType.TYPE)
    private List<Long> hostIds;

    private Long ownerId = 0L;

    private long createAt = System.currentTimeMillis();

    private BatchTaskStatus status;

    //{scriptId:{key:value}}
    @Type(type = JSONType.TYPE)
    private Map<Long, Map<String, String>> scriptEnvs;

    @Transient
    //hostTasks
    private Map<Long, BatchTask> tasks;

    @Transient
    private List<Script> scripts;

    @Transient
    private List<Host> hosts;
}
