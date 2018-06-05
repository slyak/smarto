package com.slyak.smarto.service;

import com.slyak.smarto.domain.Batch;
import com.slyak.smarto.domain.Host;
import com.slyak.smarto.dto.SysEnv;

/**
 * .
 *
 * @author stormning 2018/5/25
 * @since 1.3.0
 */
public interface SysEnvProvider<T> {

    SysEnv getMetadata();

    T provide(Batch batch, Host host);
}
