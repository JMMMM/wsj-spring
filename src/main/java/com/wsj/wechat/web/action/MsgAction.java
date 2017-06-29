package com.wsj.wechat.web.action;

import java.io.IOException;

import com.wsj.wx.api.business.MsgReceiveBusiness;
import com.opensymphony.xwork2.Action;
import com.uap.common.utils.LogUtils;
import com.uap.core.web.jmesa.action.BaseJmesaAction;

/**
 * 
 * Company:     广州快塑电子商务有限公司
 * Title:       塑问-网站
 * 类描述                           微信通知消息接收处理实现类
 * @version     1.0
 * @since:      2017年3月21日
 * @author      tonyDon
 * @serial:     ----- 变更时间 变更者 变更说明
 */
public class MsgAction extends BaseJmesaAction {
	
	/**
	 * 微信服务器推送消息处理
	 * @return
	 */
	public String msg(){
		MsgReceiveBusiness receiveBusiness = new MsgReceiveBusiness(getRequest());
		String result = receiveBusiness.execute();
		
        try {
        	getResponse().setContentType("text/xml;charset=utf-8" );   
        	getResponse().setHeader("Cache-Control" ,  "no-cache" );   
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			LogUtils.error(e);
		}
        return Action.NONE;
	}
	
}
