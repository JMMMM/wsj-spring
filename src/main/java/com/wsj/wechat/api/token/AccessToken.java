package com.wsj.wechat.api.token;

import com.wsj.wechat.api.util.WeiXinConfigure;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Access token实体模型
 */
public class AccessToken extends Token {
	
	private static Logger logger = Logger.getLogger(AccessToken.class);
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	@Override
	protected String tokenName() {
		return "access_token";
	}

	@Override
	protected String expiresInName() {
		return "expires_in";
	}

	/**
	 * 组织accesstoken的请求utl
	 */
	@Override
	protected String accessTokenUrl() {
		String appid = WeiXinConfigure.getAppid();
		String appsecret = WeiXinConfigure.getAppSecret();
		String url = ACCESS_TOKEN_URL + "&appid=" + appid + "&secret=" + appsecret;
		logger.info("创建获取access_token url");
		return url;
	}
	
	
}
