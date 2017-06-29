package com.wsj.wechat.bean.token;

import com.wsj.wechat.bean.BaseResult;

/**
 * Created by Jimmy on 2017/6/28.
 */
public class AccessToken extends BaseResult {
    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String accessToken) {
        access_token = accessToken;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expiresIn) {
        expires_in = expiresIn;
    }

}