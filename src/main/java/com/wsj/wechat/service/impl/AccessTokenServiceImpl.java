package com.wsj.wechat.service.impl;

import com.wsj.wechat.api.AccessTokenApi;
import com.wsj.wechat.entity.AccessToken;
import com.wsj.wechat.repository.AccessTokenRepository;
import com.wsj.wechat.service.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Jimmy on 2017/6/29.
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Override
    public AccessToken getAccessToken() {
        AccessToken accessToken = accessTokenRepository.getValidAccessToken();
        if (accessToken == null) {
            accessToken = AccessTokenApi.callerUrl();
            logger.info("重新获取access_token:" + accessToken);
        }
        return accessToken;
    }
}
