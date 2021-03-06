package com.slyak.smarto.service;

import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.domain.Host;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author stormning 2018/5/25
 * @since 1.3.0
 */
@Component
public class CurrentHostSysEnvProvider extends AbstractSysEnvProvider<Host> {

    @Override
    public Host provide(Batch batch, Host host) {
        return host;
    }

    @Override
    protected String getDescription() {
        return "当前主机";
    }

    @Override
    protected String getName() {
        return "CURRENT_HOST";
    }
}
