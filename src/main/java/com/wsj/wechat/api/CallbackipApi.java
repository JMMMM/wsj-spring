package com.wsj.wechat.api;

import com.wsj.tools.WsjTools;
import com.wsj.wechat.bean.callbackip.Callbackip;
import com.wsj.wechat.tools.WechatTools;

/**
 * 获取微信服务器IP地址
 *
 * @author LiYi
 */
public class CallbackipApi extends WechatApi<Callbackip> {

    public static Callbackip callerUrl(String access_token) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URI).append("/cgi-bin/getcallbackip?")
                .append(PARAM_ACCESS_TOKEN + "=" + access_token);
        String body = WechatTools.callerClient(url.toString());
        return WsjTools.jsonParser(body, Callbackip.class);
    }
}
