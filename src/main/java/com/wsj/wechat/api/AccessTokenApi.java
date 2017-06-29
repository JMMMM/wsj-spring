package com.wsj.wechat.api;

import com.wsj.tools.WsjTools;
import com.wsj.wechat.bean.token.AccessToken;
import com.wsj.wechat.tools.WechatApi;
import com.wsj.wechat.tools.WechatConfigure;
import com.wsj.wechat.tools.WechatTools;

/**
 * Created by Jimmy on 2017/6/29.
 */
public class AccessTokenApi extends WechatApi<AccessToken> {
    /**
     * 次数有限
     *
     * @return
     */
    public static AccessToken callerUrl() {
        StringBuffer accessTokenApi = new StringBuffer();
        accessTokenApi.append(WechatConfigure.BASE_URI + "/cgi-bin/token?")
                .append("grant_type=client_credential")
                .append("appid=" + WechatConfigure.getAppId())
                .append("secret=" + WechatConfigure.getAppSecrect());
        String body = WechatTools.callerClient(accessTokenApi.toString());

        return WsjTools.jsonParser(body, AccessToken.class);
    }
}
