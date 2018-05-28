package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.Batch;
import com.slyak.mirrors.domain.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * .
 *
 * @author stormning 2018/5/25
 * @since 1.3.0
 */
@Component
public class BatchHostsSysEnvProvider extends AbstractSysEnvProvider<List<Host>> {
    private final MirrorManager mirrorManager;

    @Autowired
    public BatchHostsSysEnvProvider(MirrorManager mirrorManager) {
        this.mirrorManager = mirrorManager;
    }

    @Override
    public List<Host> provide(Long batchId, Host host) {
        Batch batch = mirrorManager.findBatch(batchId);
        return batch.getHosts();
    }

    @Override
    protected String getDescription() {
        return "执行目标主机列表";
    }

    @Override
    protected String getName() {
        return "HOSTS";
    }
}
