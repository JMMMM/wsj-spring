package com.wsj.wechat.api;

public abstract class WechatApi {

    protected static String PARAM_ACCESS_TOKEN = "access_token";

    protected static final String BASE_URI = "https://api.weixin.qq.com";
    protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
    protected static final String MP_URI = "https://mp.weixin.qq.com";
    protected static final String MCH_URI = "https://api.mch.weixin.qq.com";
    protected static final String OPEN_URI = "https://open.weixin.qq.com";
    protected static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    protected static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

}
