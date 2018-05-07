package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.ProjectHost;
import com.slyak.mirrors.domain.HostGroupScript;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
public interface GroupScriptCallback {
    void processOut(ProjectHost projectHost, HostGroupScript hostGroupScript, String out);

    void processError(ProjectHost projectHost, HostGroupScript hostGroupScript, String error);
}
