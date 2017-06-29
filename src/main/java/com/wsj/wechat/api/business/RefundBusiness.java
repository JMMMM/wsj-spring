package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.refund.RefundReqData;
import com.wsj.wx.api.model.refund.RefundResData;
import com.wsj.wx.api.util.WeiXinConfigure;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 申请退款业务
 */
public class RefundBusiness extends BaseBusiness {

    public RefundBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<RefundResData> refund(RefundReqData refundReqData) {
        if (refundReqData.getOp_user_id() == null) {
            refundReqData.setOp_user_id(WeiXinConfigure.getMchID());
        }
        initCommonData(refundReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.REFUND_API, refundReqData, httpClientHolder);
        RefundResData refundResData = XmlUtil.getObjectFromXML(result, RefundResData.class);
        return dealResult(refundResData, result);
    }

}
