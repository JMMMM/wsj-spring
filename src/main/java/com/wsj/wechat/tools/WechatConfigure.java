package com.wsj.wechat.tools;

/**
 * 微信公众号配置信息
 */
public class WechatConfigure {

    private static String appId;

    private static String appSecrect;

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        WechatConfigure.appId = appId;
    }

    public static String getAppSecrect() {
        return appSecrect;
    }

    public void setAppSecrect(String appSecrect) {
        WechatConfigure.appSecrect = appSecrect;
    }
}
