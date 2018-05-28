package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.Batch;
import com.slyak.mirrors.domain.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * .
 *
 * @author stormning 2018/5/25
 * @since 1.3.0
 */
@Component
public class BatchHostsExcludeSelfSysEnvProvider extends AbstractSysEnvProvider<List<Host>> {
    private final MirrorManager mirrorManager;

    @Autowired
    public BatchHostsExcludeSelfSysEnvProvider(MirrorManager mirrorManager) {
        this.mirrorManager = mirrorManager;
    }

    @Override
    public List<Host> provide(Long batchId, Host host) {
        Batch batch = mirrorManager.findBatch(batchId);
        List<Host> hosts = batch.getHosts();
        for (int i = hosts.size() - 1; i >= 0; i--) {
            if (Objects.equals(host.getId(), hosts.get(i).getId())) {
                hosts.remove(i);
            }
        }
        return hosts;
    }

    @Override
    protected String getDescription() {
        return "除本机之外的目标主机列表";
    }

    @Override
    protected String getName() {
        return "HOSTS_EXCLUDE_SELF";
    }
}
