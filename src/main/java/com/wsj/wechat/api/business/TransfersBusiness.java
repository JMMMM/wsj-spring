package com.wsj.wechat.api.business;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.transfers.TransfersReqData;
import com.wsj.wx.api.model.transfers.TransfersResData;
import com.wsj.wx.api.util.SignUtil;
import com.wsj.wx.api.util.WeiXinConfigure;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 企业付款业务
 */
public class TransfersBusiness extends BaseBusiness {


    public TransfersBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    /**
     * 企业付款方法
     *
     * @param transfersReqData 企业付款参数
     * @return 企业付款结果
     */
    public TransfersResData transfers(TransfersReqData transfersReqData) {
        transfersReqData.setMch_appid(WeiXinConfigure.getAppID());
        transfersReqData.setMchid(WeiXinConfigure.getMchID());
        transfersReqData.setSpbill_create_ip(WeiXinConfigure.getIp());
        String sign = SignUtil.genSign(transfersReqData, WeiXinConfigure.getKey());
        transfersReqData.setSign(sign);
        
        String result = HttpSend.sendRequest(WeiXinPayUrl.TRANSFERS_API, transfersReqData, httpClientHolder);
        TransfersResData transfersResData = XmlUtil.getObjectFromXML(result, TransfersResData.class);
        return transfersResData;
    }


}
