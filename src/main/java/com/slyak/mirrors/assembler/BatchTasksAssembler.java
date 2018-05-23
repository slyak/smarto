package com.slyak.mirrors.assembler;

import com.google.common.collect.Maps;
import com.slyak.mirrors.domain.Batch;
import com.slyak.mirrors.domain.BatchTask;
import com.slyak.mirrors.domain.BatchTaskKey;
import com.slyak.mirrors.repository.BatchTaskRepository;
import com.slyak.spring.jpa.EntityAssemblerMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author stormning 2018/5/11
 * @since 1.3.0
 */
@Component
public class BatchTasksAssembler extends EntityAssemblerMany<Batch, BatchTaskKey, BatchTask> {
    @Autowired
    private BatchTaskRepository batchTaskRepository;

    @Override
    protected void setValue(Batch bean, List<BatchTask> value) {
        Map<Long, BatchTask> hostTasks = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(value)) {
            for (BatchTask task : value) {
                hostTasks.put(task.getId().getHostId(), task);
            }
        }
        bean.setTasks(hostTasks);
    }

    @Override
    protected List<BatchTask> getValue(List<BatchTaskKey> keys) {
        return batchTaskRepository.findAll(keys);
    }

    @Override
    protected List<BatchTaskKey> getKeys(Batch bean) {
        return bean.getHostIds().stream().map(hostId -> {
            BatchTaskKey taskKey = new BatchTaskKey();
            taskKey.setBatchId(bean.getId());
            taskKey.setHostId(hostId);
            return taskKey;
        }).collect(Collectors.toList());
    }

    @Override
    protected Map<BatchTaskKey, BatchTask> mgetValue(Collection<BatchTaskKey> keys) {
        return batchTaskRepository.mget(keys);
    }

}