package com.slyak.smarto.service;

import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.domain.Host;
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

    @Override
    public List<Host> provide(Batch batch, Host host) {
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
