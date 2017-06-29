package com.wsj.wechat.api.business;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wsj.wx.api.exception.WeChatException;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.user.Qrcode;
import com.wsj.wx.api.model.user.QrcodeType;
import com.wsj.wx.api.token.server.AccessTokenServer;
import com.wsj.wx.api.util.WeChatUtil;

/**
 * 二维码创建业务
 */
public class QrcodeBusiness {
	
	Logger logger = Logger.getLogger(QrcodeBusiness.class);
	
	//长链接转短链接接口
	private static final String SHORTURL_POST_URL="https://api.weixin.qq.com/cgi-bin/shorturl?access_token=";
	//生成带参数的二维码
	private static final String QRCODE_POST_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	//通过ticket换取二维码
	private static final String SHOWQRCODE_POST_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	
	private String accessToken;
	
	public QrcodeBusiness(){
		this.accessToken = AccessTokenServer.instance().token();
	}
	
	/**
	 * 长链接转短链接接口
	 * @param longUrl 需要转换的长链接
	 * @return
	 */
	public String shortUrl(String longUrl){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action", "long2short");
		jsonObject.put("long_url", longUrl);
		String requestData = jsonObject.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpSend.post(SHORTURL_POST_URL + this.accessToken, requestData);
		logger.info("return data "+resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		JSONObject resultJson=JSONObject.parseObject(resultStr);
		return resultJson.getString("short_url");
	}
	
	/**
	 * 创建永久二维码
	 * @param scene_id 场景值ID,目前参数只支持1--100000
	 * @return
	 */
	public Qrcode createQrcodePerpetual(long sceneId){
		return createQrcodeTicket(QrcodeType.QR_LIMIT_SCENE, null, sceneId,null);
	}
	
	/**
	 * 创建永久二维码
	 * @param scene_str 场景值ID,长度限制为1到64
	 * @return
	 */
	public Qrcode createQrcodePerpetualstr(String sceneStr){
		return createQrcodeTicket(QrcodeType.QR_LIMIT_STR_SCENE, null, null,sceneStr);
	}
	
	/**
	 * 创建临时二维码
	 * @param scene_id 场景值ID
	 * @param expire_seconds 二维码有效时间，以秒为单位,最大不超过604800（即7天）。
	 * @return
	 */
	public Qrcode createQrcodeTemporary(long sceneId,int expireSeconds){
		return createQrcodeTicket(QrcodeType.QR_SCENE, expireSeconds, sceneId,null);
	}
	
	private Qrcode createQrcodeTicket(QrcodeType qrcodeType,Integer expireSeconds,Long sceneId,String sceneStr){
		JSONObject ticketJson = new JSONObject();
		ticketJson.put("action_name", qrcodeType);
		JSONObject sceneJson = new JSONObject();
		switch (qrcodeType) {
			case QR_SCENE:
				ticketJson.put("expire_seconds", expireSeconds);
				sceneJson.put("scene_id", sceneId);
				break;
			case QR_LIMIT_SCENE:
				sceneJson.put("scene_id", sceneId);
				break;
			case QR_LIMIT_STR_SCENE:
				sceneJson.put("scene_str", sceneStr);
				break;
		}
		JSONObject actionInfoJson = new JSONObject();
		actionInfoJson.put("scene", sceneJson);
		ticketJson.put("action_info", actionInfoJson);
		String requestData = ticketJson.toString();
		logger.info("request data "+requestData);
		String resultStr = HttpSend.post(QRCODE_POST_URL + this.accessToken, requestData);
		logger.info("return data "+resultStr);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Qrcode qrcode = JSONObject.parseObject(resultStr,Qrcode.class);
		return qrcode;
	}
	
//	/**
//	 * 换取二维码
//	 * @param ticket
//	 * @param qrcodeFile 二维码存储路径
//	 */
//	public static void getQrcode(String ticket,String qrcodeFile){
//		try {
//			byte[] b = HttpSend.getFile(SHOWQRCODE_POST_URL+URLEncoder.encode(ticket, "UTF-8"));
//			File file = new File(qrcodeFile);
//			FileOutputStream fStream = new FileOutputStream(file);
//			fStream.write(b);
//			fStream.flush();
//			fStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
