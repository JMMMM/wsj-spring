package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.prepay.PrePayReqData;
import com.wsj.wx.api.model.prepay.PrePayResData;
import com.wsj.wx.api.util.WeiXinConfigure;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 预支付业务
 */
public class PrePayBusiness extends BaseBusiness {

    public PrePayBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<PrePayResData> prePay(PrePayReqData prePayReqData) {
    	prePayReqData.setNotify_url(WeiXinConfigure.getNotifyUrl());
    	prePayReqData.setSpbill_create_ip(WeiXinConfigure.getIp());
        initCommonData(prePayReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.PRE_PAY_API, prePayReqData, httpClientHolder);
        PrePayResData prePayResData = XmlUtil.getObjectFromXML(result, PrePayResData.class);
        return dealResult(prePayResData, result);
    }

}
