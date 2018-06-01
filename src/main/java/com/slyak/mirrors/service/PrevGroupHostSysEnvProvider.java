package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.Host;

/**
 * .
 *
 * @author stormning 2018/6/1
 * @since 1.3.0
 */
public class PrevGroupHostSysEnvProvider extends AbstractSysEnvProvider<Host> {
    @Override
    public Host provide(Long batchId, Host host) {
        return null;
    }

    @Override
    protected String getDescription() {
        return null;
    }

    @Override
    protected String getName() {
        return null;
    }
}
