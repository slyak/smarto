package com.slyak.smarto.service;

import com.google.common.collect.Lists;
import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.domain.Host;
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

    @Override
    public List<Host> provide(Batch batch, Host host) {
        List<Host> cloned = Lists.newArrayList();
        List<Host> hosts = batch.getHosts();
        for (int i = hosts.size() - 1; i >= 0; i--) {
            Host h = hosts.get(i);
            if (!Objects.equals(host.getId(), h.getId())) {
                cloned.add(h);
            }
        }
        return cloned;
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
