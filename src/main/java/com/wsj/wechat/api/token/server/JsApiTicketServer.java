package com.wsj.wechat.api.token.server;

import com.wsj.wx.api.token.Ticket;
import com.wsj.wx.api.token.TicketType;
import com.uap.common.utils.AppContextUtils;
import com.uap.core.domain.dao.IRedisDao;

/**
 * Ticket server适配器
 */
public class JsApiTicketServer implements TicketServer  {

	private static JsApiTicketServer ticketServer = new JsApiTicketServer();
	
	private Ticket jsApiTicket = new Ticket(TicketType.jsapi);
	
	private int requestTimes = 3;//token请求失败后重新请求的次数
	
	/**
	 * 私有构造
	 */
	private JsApiTicketServer(){
	}
	
	/**
	 * token中控服务器实例
	 * @return ticket服务器实例
	 */
	public static JsApiTicketServer instance(){
		return ticketServer;
	}
	
	/**
	 * 通过中控服务器得到accessToken
	 * @return
	 */
	public String ticket(){
		IRedisDao redisDao = (IRedisDao) AppContextUtils.getBean(IRedisDao.class.getName());
		String ticketJson = (String) redisDao.getHash("ACCESS_TOKEN", "WX_JSAPI_TICKET"); 
		jsApiTicket.parseData(ticketJson);
		
		if(!this.jsApiTicket.isValid()){
			refresh();
			redisDao.addHash("ACCESS_TOKEN", "WX_JSAPI_TICKET", jsApiTicket.getData());
		}
		
		return jsApiTicket.getToken();
	}
	
	/**
	 * 服务器刷新token
	 */
	private void refresh(){
		for(int i=0;i<requestTimes;i++){
			//请求成功则退出
			if(this.jsApiTicket.request())
				break;
		}
	}

}
