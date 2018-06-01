package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.Batch;
import com.slyak.mirrors.domain.Host;
import com.slyak.mirrors.dto.SysEnv;

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
