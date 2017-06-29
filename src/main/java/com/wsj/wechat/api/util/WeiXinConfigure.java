package com.wsj.wechat.api.util;

import com.uap.common.utils.PropertisFileUtils;

/**
 * 微信公众号配置信息
 */
public class WeiXinConfigure {

    // 私有Key
    // 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
    // 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
    public static String getKey() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.key");
    }

    //微信分配的公众号ID
    public static String getAppID() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.appid");
    }

    //微信支付分配的商户号ID
    public static String getMchID() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.mchid");
    }

    //受理模式下给子商户分配的子商户号
    public static String getSubMchID() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.submchid");
    }

    //HTTPS证书的本地路径
    public static String getCertLocalPath() {
        return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.certlocalpath");
    }

    //HTTPS证书密码，默认密码等于商户号MCHID
    public static String getCertPassword() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.certpassword");
    }

    //机器IP
    public static String getIp() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.spbill_create_ip");
    }
    
    //接收微信服务器回调通知消息地址
    public static String getNotifyUrl() {
    	return PropertisFileUtils.getInstance().getSystemPropertyValue("wx.notify_url");
    }

    //接收微信服务器事件消息通知地址
	public static String getUrl() {
		return PropertisFileUtils.getInstance().getSystemPropertyValue("wechat.url");
	}

	//token令牌
	public static String getToken() {
		return PropertisFileUtils.getInstance().getSystemPropertyValue("wechat.token");
	}

	//消息加密密钥
	public static String getEncodingAESKey() {
		return PropertisFileUtils.getInstance().getSystemPropertyValue("wechat.encodingaeskey");
	}

	//公众号应用ID
	public static String getAppid() {
		return PropertisFileUtils.getInstance().getSystemPropertyValue("wechat.appid");
	}

	//公众号应用密钥
	public static String getAppSecret() {
		return PropertisFileUtils.getInstance().getSystemPropertyValue("wechat.appsecret");
	}
    
    
}
