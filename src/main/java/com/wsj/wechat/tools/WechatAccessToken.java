package com.wsj.wechat.tools;

import com.wsj.wechat.api.AccessTokenApi;
import com.wsj.wechat.entity.AccessToken;
import com.wsj.wechat.repository.AccessTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by Jimmy on 2017/6/29.
 */
@Component
public class WechatAccessToken {
    private static final Logger logger = LoggerFactory.getLogger(WechatAccessToken.class);
    private static AccessTokenRepository accessTokenRepository;

    @Autowired
    private void setAccessTokenRepository(AccessTokenRepository accessTokenRepository) {
        WechatAccessToken.accessTokenRepository = accessTokenRepository;
    }

    public static AccessToken getAccessToken() {
        AccessToken accessToken = accessTokenRepository.getValidAccessToken();
        if (accessToken == null|| StringUtils.isEmpty(accessToken.getAccess_token())) {
            accessToken = AccessTokenApi.callerUrl();
            Assert.isTrue(!StringUtils.isEmpty(accessToken.getAccess_token()),"获取accessToken失败!");
            accessTokenRepository.save(accessToken);
            logger.info("重新获取access_token:" + accessToken);
        }
        return accessToken;
    }
}
