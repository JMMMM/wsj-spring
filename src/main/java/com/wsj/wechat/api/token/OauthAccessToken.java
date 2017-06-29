package com.wsj.wechat.api.token;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wsj.wx.api.util.WeiXinConfigure;
import com.uap.common.utils.ObjectUtils;

/**
 * 
 * Company:     广州快塑电子商务有限公司
 * Title:       塑问-网站
 * 类描述                           获取微信网页授权accessToken
 * @version     1.0
 * @since:      2017年4月17日
 * @author      tonyDon
 * @serial:     ----- 变更时间 变更者 变更说明
 */
public class OauthAccessToken extends Token {
	
	private static Logger logger = Logger.getLogger(OauthAccessToken.class);
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	private String code;
	
	public OauthAccessToken(String code) {
		this.code = code;
	}
	
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
		String url = ACCESS_TOKEN_URL + "?appid=" + appid + "&secret=" + appsecret+"&code="+this.code+"&grant_type=authorization_code";
		logger.info("创建获取access_token url");
		return url;
	}
	
	public String getOpenId(){
		String data = getData();
		if(ObjectUtils.isNull(data)){
			return "";
		}
		
		JSONObject jsonObject = JSONObject.parseObject(data);
		try {
			if(ObjectUtils.isNotNull(jsonObject.get("openid"))){
				return jsonObject.get("openid").toString();
			}
		} catch (Exception e) {
			logger.error("openid 结果解析失败，openid参数名称: openid" + "openid请求结果:" + data);
			e.printStackTrace();
		}
		
		return "";
	}
	
}
