package com.wsj.sys.enums;

/**
 * Created by jimmy on 2017/6/25.
 */
public enum SysConstants {

    ManagerLoginSession("staffInfo"),
    WebLoginSession("customerInfo"),
    IndexPath("/wsj_system/index.html"),
    LoginPath("/wsj_system/login/login.html"),
    WsjWxOpenId("wsj_wx_openId");
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
