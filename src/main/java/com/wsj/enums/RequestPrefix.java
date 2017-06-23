package com.wsj.enums;

/**
 * Created by Jimmy on 2017/6/23.
 */
public enum RequestPrefix {
    Manager("/manager/"), Web("/web/");

    private String prefix;

    private RequestPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
