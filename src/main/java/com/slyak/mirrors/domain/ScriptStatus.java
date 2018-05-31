package com.slyak.mirrors.domain;

import lombok.Getter;

/**
 * .
 *
 * @author stormning 2018/5/30
 * @since 1.3.0
 */
public enum ScriptStatus {
    UNKNOWN("未测试"), SUCCESS("成功"), FAILED("失败");

    @Getter
    private String title;

    ScriptStatus(String title) {
        this.title = title;
    }
}
