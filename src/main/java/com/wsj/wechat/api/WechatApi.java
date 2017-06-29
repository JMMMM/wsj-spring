package com.wsj.wechat.api;

public abstract class WechatApi<T> {

    protected static String PARAM_ACCESS_TOKEN = "access_token";

    public static final String BASE_URI = "https://api.weixin.qq.com";
    public static final String MEDIA_URI = "http://file.api.weixin.qq.com";
    public static final String MP_URI = "https://mp.weixin.qq.com";
    public static final String MCH_URI = "https://api.mch.weixin.qq.com";
    public static final String OPEN_URI = "https://open.weixin.qq.com";

}
