package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.query.OrderQueryReqData;
import com.wsj.wx.api.model.query.OrderQueryResData;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 订单查询业务
 */
public class OrderQueryBusinss extends BaseBusiness {

    public OrderQueryBusinss(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<OrderQueryResData> orderQuery(OrderQueryReqData orderQueryReqData) {
        initCommonData(orderQueryReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.PAY_QUERY_API, orderQueryReqData, httpClientHolder);
        OrderQueryResData orderQueryResData = XmlUtil.getObjectFromXML(result, OrderQueryResData.class);
        return dealResult(orderQueryResData, result);
    }

}
