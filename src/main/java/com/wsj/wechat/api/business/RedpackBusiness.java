package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.redpack.RedpackReqData;
import com.wsj.wx.api.model.redpack.RedpackResData;
import com.wsj.wx.api.model.transfers.TransfersResData;
import com.wsj.wx.api.util.SignUtil;
import com.wsj.wx.api.util.WeiXinConfigure;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 
 * Company:     广州快塑电子商务有限公司
 * Title:       塑问-网站
 * 类描述                           红包业务
 * @version     1.0
 * @since:      2017年5月4日
 * @author      tonyDon
 * @serial:     ----- 变更时间 变更者 变更说明
 */
public class RedpackBusiness extends BaseBusiness {

	public RedpackBusiness(HttpClientHolder httpClientHolder) {
		super(httpClientHolder);
	}

	/**
	 * 发放普通红包
	 */
	public RedpackResData sendredpack(RedpackReqData redpackReqData){
		redpackReqData.setMch_id(WeiXinConfigure.getMchID());
		redpackReqData.setWxappid(WeiXinConfigure.getAppID());
		redpackReqData.setClient_ip(WeiXinConfigure.getIp());
        String sign = SignUtil.genSign(redpackReqData, WeiXinConfigure.getKey());
        redpackReqData.setSign(sign);
        
        String result = HttpSend.sendRequest(WeiXinPayUrl.SENDREDPACK_API, redpackReqData, httpClientHolder);
        RedpackResData redpackResData = XmlUtil.getObjectFromXML(result, RedpackResData.class);
        return redpackResData;
	}
	
}
