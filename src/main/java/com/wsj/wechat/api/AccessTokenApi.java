package com.wsj.wechat.api;

import com.wsj.tools.WsjTools;
import com.wsj.wechat.entity.AccessToken;
import com.wsj.wechat.tools.WechatConfigure;
import com.wsj.wechat.tools.WechatTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jimmy on 2017/6/29.
 */
public class AccessTokenApi extends WechatApi {
    private static Logger logger = LoggerFactory.getLogger(AccessTokenApi.class);

    /**
     * 次数有限
     *
     * @return
     */
    public static AccessToken callerUrl() {
        StringBuffer accessTokenApi = new StringBuffer();
        accessTokenApi.append(BASE_URI + "/cgi-bin/token?")
                .append("grant_type=client_credential&")
                .append("appid=" + WechatConfigure.getAppId() + "&")
                .append("secret=" + WechatConfigure.getAppSecrect());
        String body = WechatTools.callerClient(accessTokenApi.toString());
        logger.info(AccessToken.class.getSimpleName() + ":" + body);
        return WsjTools.jsonParser(body, AccessToken.class);
    }
}
