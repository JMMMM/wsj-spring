package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.micropay.MicroPayReqData;
import com.wsj.wx.api.model.micropay.MicroPayResData;
import com.wsj.wx.api.util.XmlUtil;


/**
 * 刷卡支付业务
 */
public class MicroPayBusinessSimple extends BaseBusiness {

    public MicroPayBusinessSimple(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<MicroPayResData> microPay(MicroPayReqData microPayReqData) {
        initCommonData(microPayReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.MICRO_PAY_API, microPayReqData, httpClientHolder);
        MicroPayResData microPayResData = XmlUtil.getObjectFromXML(result, MicroPayResData.class);
        return this.dealResult(microPayResData, result);
    }

}
