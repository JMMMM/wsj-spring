package com.wsj.sys.enums;

/**
 * Created by jimmy on 2017/6/25.
 */
public enum SysConstants {

    ManagerLoginSession("staffInfo"),
    WebLoginSession("customerInfo"),
    IndexPath("/wsj_system/index.html"),
    LoginPath("/wsj_system/login/login.html"),
    WebLoginPath("/wsy_system/wechat/view/login/login.html"),
    NotFound("/wsy_system/wechat/view/index.html"),
    ChangeNikeName("/wsy_system/wechat/view/login/change_nickname.html"),
    ServerError(""),
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
