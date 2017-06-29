package com.wsj.wechat.api.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.model.common.CommonResData;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.util.SignUtil;
import com.wsj.wx.api.util.WeiXinConfigure;

/**
 * 基础业务类
 */
public class BaseBusiness {

    private static Logger logger = LoggerFactory.getLogger(BaseBusiness.class);

    protected HttpClientHolder httpClientHolder;

    public BaseBusiness(HttpClientHolder httpClientHolder) {
        this.httpClientHolder = httpClientHolder;
    }

    protected void initCommonData(CommonReqData commonReqData) {
        commonReqData.setAppid(WeiXinConfigure.getAppID());
        commonReqData.setMch_id(WeiXinConfigure.getMchID());
        String sign = SignUtil.genSign(commonReqData, WeiXinConfigure.getKey());
        commonReqData.setSign(sign);
    }

    protected <T extends CommonResData> WeiXinResult<T> dealResult(T commonResData, String result) {
        if (commonResData == null || commonResData.getReturn_code() == null) {
            String errorStr = "请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问";
            logger.error(errorStr);
            return WeiXinResult.error(errorStr, commonResData);
        }
        if (commonResData.getReturn_code().equals("FAIL")) {
            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            return WeiXinResult.error(commonResData.getReturn_msg(), commonResData);
        } else {
            if (!SignUtil.checkSign(result, WeiXinConfigure.getKey())) {
                String errorMessage = "请检测Post给API的数据是否规范合法或者数据被篡改了";
                logger.error(errorMessage);
                return WeiXinResult.error(errorMessage, commonResData);
            } else {
                if (commonResData.getResult_code().equals("SUCCESS")) {
                    return WeiXinResult.success(commonResData);
                } else {//出现业务错误
                    String errorCode = commonResData.getErr_code();//获取错误码
                    String errorCodeDes = commonResData.getErr_code_des();//获取错误描述
                    logger.error("业务返回失败,err_code:{},err_code_des:{}", errorCode, errorCodeDes);
                    return WeiXinResult.failure("业务失败" + errorCodeDes, commonResData);
                }
            }
        }
    }

}
