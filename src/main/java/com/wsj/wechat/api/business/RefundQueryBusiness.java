package com.wsj.wechat.api.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.query.RefundQueryReqData;
import com.wsj.wx.api.model.query.RefundQueryResData;
import com.wsj.wx.api.model.query.RefundQueryResFee;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 退款查询业务
 */
public class RefundQueryBusiness extends BaseBusiness {

    public RefundQueryBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<RefundQueryResData> refundQuery(RefundQueryReqData refundQueryReqData) {
        initCommonData(refundQueryReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.REFUND_QUERY_API, refundQueryReqData, httpClientHolder);
        RefundQueryResData refundQueryResData = XmlUtil.getObjectFromXML(result, RefundQueryResData.class);
        WeiXinResult<RefundQueryResData> commonResData = dealResult(refundQueryResData, result);
        if (commonResData.isSuccess()) {
            Map<String, String> map = XmlUtil.getMapFromXML(result);
            if (refundQueryResData.getRefund_count() != null && refundQueryResData.getRefund_count() > 0) {
                List<RefundQueryResFee> list = new ArrayList<RefundQueryResFee>();
                for (int i = 0; i < refundQueryResData.getRefund_count(); i++) {
                    String out_refund_no = (String) map.get("out_refund_no_" + i);
                    String refund_id = (String) map.get("refund_id_" + i);
                    Object refund_channel_object = map.get("refund_channel_" + i);
                    String refund_channel = refund_channel_object == null ? null : (String) refund_channel_object;
                    Integer refund_fee = Integer.valueOf((String) map.get("refund_fee_" + i));
                    String refund_status = (String) map.get("refund_status_" + i);
                    String refund_recv_accout = (String) map.get("refund_recv_accout_" + i);
                    RefundQueryResFee refundQueryResFee = new RefundQueryResFee();
                    refundQueryResFee.setOut_refund_no(out_refund_no);
                    refundQueryResFee.setRefund_id(refund_id);
                    refundQueryResFee.setRefund_channel(refund_channel);
                    refundQueryResFee.setRefund_fee(refund_fee);
                    refundQueryResFee.setRefund_status(refund_status);
                    refundQueryResFee.setRefund_recv_accout(refund_recv_accout);
                    list.add(refundQueryResFee);
                }
                refundQueryResData.setResFeeList(list);
            }
        }
        return commonResData;
    }

}
