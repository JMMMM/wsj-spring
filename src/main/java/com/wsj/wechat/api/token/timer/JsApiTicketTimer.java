package com.wsj.wechat.api.token.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.wsj.wx.api.token.server.JsApiTicketServer;

public class JsApiTicketTimer extends TimerTask {

	private static Logger logger = Logger.getLogger(JsApiTicketTimer.class);
	// jsapi_ticket有效期7200秒,提前200秒请求新的token
	public static final long PERIOD = 7000 * 1000;
	public static final long DELAY = 0; // 此任务的延迟时间为0，即立即执行

	@Override
	public void run() {
		logger.info("jsapi_ticket 定时任务启动，获取新的jsapi_ticket");
		// 得到新的access token
		// 手动获取成功之后持久化accessToken
		JsApiTicketServer jsapiTicketServer = JsApiTicketServer.instance();
		jsapiTicketServer.ticket();
	}

}
