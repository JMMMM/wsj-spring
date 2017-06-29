package com.wsj.wechat.tools;

/**
 * 微信公众号配置信息
 */
public class WechatConfigure {

    private static String appId;

    private static String appSecrect;

    public static final String BASE_URI = "https://api.weixin.qq.com";
    public static final String MEDIA_URI = "http://file.api.weixin.qq.com";
    public static final String MP_URI = "https://mp.weixin.qq.com";
    public static final String MCH_URI = "https://api.mch.weixin.qq.com";
    public static final String OPEN_URI = "https://open.weixin.qq.com";

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
