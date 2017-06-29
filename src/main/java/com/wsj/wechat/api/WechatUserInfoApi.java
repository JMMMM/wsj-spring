package com.wsj.wechat.api;

import com.wsj.tools.WsjTools;
import com.wsj.wechat.bean.user.UserInfo;
import com.wsj.wechat.tools.WechatTools;

/**
 * Created by Jimmy on 2017/6/29.
 */
public class WechatUserInfoApi extends WechatApi {

    public static UserInfo callerUrl(String accessToken, String openId) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URI + "/cgi-bin/user/info?")
                .append(PARAM_ACCESS_TOKEN + "=" + accessToken + "&")
                .append("openid=" + openId + "&")
                .append("lang=zh_CN");
        String body = WechatTools.callerClient(url.toString());
        return WsjTools.jsonParser(body, UserInfo.class);
    }
}
