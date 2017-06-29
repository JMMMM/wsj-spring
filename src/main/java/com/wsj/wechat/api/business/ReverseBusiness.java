package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.reverse.ReverseReqData;
import com.wsj.wx.api.model.reverse.ReverseResData;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 撤销订单业务
 */
public class ReverseBusiness extends BaseBusiness {

    /**
     * 撤销订单业务构造方法
     *
     * @param httpClientHolder   微信支付请求持有者
     * @param weiXinConfigure 微信支付配置信息
     */
    public ReverseBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    /**
     * 撤销订单方法
     *
     * @param reverseReqData 撤销订单参数
     * @return 撤销订单结果
     */
    public WeiXinResult<ReverseResData> reverse(ReverseReqData reverseReqData) {
        initCommonData(reverseReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.REVERSE_API, reverseReqData, httpClientHolder);
        ReverseResData reverseResData = XmlUtil.getObjectFromXML(result, ReverseResData.class);
        return dealResult(reverseResData, result);
    }

}