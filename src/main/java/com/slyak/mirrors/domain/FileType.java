package com.slyak.mirrors.domain;

/**
 * .
 *
 * @author stormning 2018/4/16
 * @since 1.3.0
 */
public enum FileType {
    NORMAL("文件"), URL("URL"), YUM("YUM");

    private final String display;

    FileType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}