package com.wsj.wechat.api.business;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wsj.wx.api.exception.WeChatException;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.user.LanguageType;
import com.wsj.wx.api.model.user.UserInfo;
import com.wsj.wx.api.token.server.AccessTokenServer;
import com.wsj.wx.api.util.WeChatUtil;

/**
 * 用户管理业务
 */
public class UserBusiness {

	Logger logger = Logger.getLogger(UserBusiness.class);
	private String accessToken;
	// 设置用户备注名
	private static final String USER_UPDATE_REMARK_POST_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=";
	// 获取用户基本信息
	private static final String USER_INFO_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	// 授权后，获取用户基本信息
	private static final String SNS_USER_INFO_GET_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=";

	public UserBusiness() {
		this.accessToken = AccessTokenServer.instance().token();
	}

	/**
	 * 设置用户备注名
	 * 
	 * @param openid
	 *            用户openid
	 * @param remark
	 *            新的备注名，长度必须小于30字符
	 * @return
	 * @throws WeChatException
	 */
	public void updateRemark(String openId, String remark)
			throws WeChatException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("remark", remark);
		String requestData = jsonObject.toString();
		logger.info("request data " + requestData);
		String resultStr = HttpSend.post(USER_UPDATE_REMARK_POST_URL+ this.accessToken, requestData);
		logger.info("return data " + resultStr);
		WeChatUtil.isSuccess(resultStr);
	}

	/**
	 * 获取用户基本信息
	 * 
	 * @param openid
	 *            普通用户的标识，对当前公众号唯一
	 * @return
	 */
	public UserInfo getUserInfo(String openId) {
		return getUserInfo(openId, null);
	}

	/**
	 * 获取用户基本信息
	 * 
	 * @param openid
	 *            普通用户的标识，对当前公众号唯一
	 * @param lang
	 *            返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	public UserInfo getUserInfo(String openId, LanguageType lang) {
		String url = USER_INFO_GET_URL + this.accessToken + "&openid=" + openId;
		if (lang != null) {
			url += "&lang=" + lang.name();
		}
		String resultStr = HttpSend.get(url);
		logger.info("return data " + resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		UserInfo user = JSONObject.parseObject(resultStr, UserInfo.class);
		return user;
	}

	/**
	 * 获取用户基本信息
	 * 
	 * @param openid
	 *            普通用户的标识，对当前公众号唯一
	 * @param lang
	 *            返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	public UserInfo getSnsUserInfo(String token, String openId) {
		String url = SNS_USER_INFO_GET_URL + token + "&openid=" + openId + "&lang=zh_CN";
		String resultStr = HttpSend.get(url);
		logger.info("return data " + resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return JSONObject.parseObject(resultStr, UserInfo.class);
	}
	
}
