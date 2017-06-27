package com.wsj.sys.enums;

/**
 * Created by jimmy on 2017/6/25.
 */
public enum SysConstants {

    LoginSession("staffInfo"),
    IndexPath("/wsj/index.html"),
    LoginPath("/wsj/login/login.html");
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
