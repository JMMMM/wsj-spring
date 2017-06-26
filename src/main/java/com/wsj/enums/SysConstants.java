package com.wsj.enums;

/**
 * Created by jimmy on 2017/6/25.
 */
public enum SysConstants {

    LoginSession("staffInfo"),
    LoginPath("/login");
    private String name;
    private SysConstants(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
