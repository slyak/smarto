package com.slyak.mirrors.service;

import com.slyak.mirrors.domain.GroupHost;
import com.slyak.mirrors.domain.GroupScript;

/**
 * .
 *
 * @author stormning 2018/4/27
 * @since 1.3.0
 */
public interface GroupScriptCallback {
    void processOut(GroupHost groupHost, GroupScript groupScript, String out);

    void processError(GroupHost groupHost, GroupScript groupScript, String error);
}
