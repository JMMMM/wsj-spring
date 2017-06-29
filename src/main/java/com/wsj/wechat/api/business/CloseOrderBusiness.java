package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.close.CloseOrderReqData;
import com.wsj.wx.api.model.close.CloseOrderResData;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 关闭订单业务
 */
public class CloseOrderBusiness extends BaseBusiness {

    public CloseOrderBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<CloseOrderResData> closeOrder(CloseOrderReqData closeOrderReqData) {
        initCommonData(closeOrderReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.CLOSE_ORDER_API, closeOrderReqData, httpClientHolder);
        CloseOrderResData closeOrderResData = XmlUtil.getObjectFromXML(result, CloseOrderResData.class);
        return dealResult(closeOrderResData, result);
    }

}
