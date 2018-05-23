package com.slyak.mirrors.domain;

import lombok.Getter;

/**
 * .
 *
 * @author stormning 2018/5/18
 * @since 1.3.0
 */
public enum BatchTaskStatus {
    RUNNING("运行中"), SUCCESS("成功"), FAILED("失败");

    @Getter
    private String title;

    BatchTaskStatus(String title) {
        this.title = title;
    }
}
