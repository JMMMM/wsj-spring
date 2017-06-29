package com.wsj.wechat.api.token.server;

import com.wsj.wx.api.token.AccessToken;
import com.uap.common.utils.AppContextUtils;
import com.uap.core.domain.dao.IRedisDao;


/**
 * 适配器
 */
public class AccessTokenServer implements TokenServer {

	
	private static AccessTokenServer tokenServer = new AccessTokenServer();
	
	private AccessToken accessToken = new AccessToken();
	
	private int requestTimes = 3;//token请求失败后重新请求的次数
	
	/**
	 * 私有构造
	 */
	private AccessTokenServer(){
	}
	
	/**
	 * token中控服务器实例
	 * @return 中控服务器实例
	 */
	public static AccessTokenServer instance(){
		return tokenServer;
	}
	
	/**
	 * 通过中控服务器得到accessToken
	 * @return
	 */
	public String token(){
		IRedisDao redisDao = (IRedisDao) AppContextUtils.getBean(IRedisDao.class.getName());
		String ticketJson = (String) redisDao.getHash("ACCESS_TOKEN", "WX_ACCESS_TOKEN"); 
		accessToken.parseData(ticketJson);
		
		if(!this.accessToken.isValid()){
			refresh();
			redisDao.addHash("ACCESS_TOKEN", "WX_ACCESS_TOKEN", accessToken.getData());
		} 
		return accessToken.getToken();
	}
	
	/**
	 * 服务器刷新token
	 */
	private void refresh(){
		for(int i=0;i<requestTimes;i++){
			//请求成功则退出
			if(this.accessToken.request())
				break;
		}
	}
	
}
