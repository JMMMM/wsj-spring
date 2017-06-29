package com.wsj.wechat.api.util;

import com.alibaba.fastjson.JSONObject;
import com.wsj.wechat.api.exception.WeChatException;
import com.wsj.wechat.api.exception.WeChatReturnCode;

/**
 * 工具类
 */
public class WeChatUtil {

    /**
     * 判断是否请求成功
     *
     * @param resultStr
     * @throws WeChatException
     */
    public static void isSuccess(String resultStr) throws WeChatException {
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        Integer errCode = jsonObject.getIntValue("errcode");
        if (errCode != null && errCode != 0) {
            String errMsg = WeChatReturnCode.getMsg(errCode);
            if (errMsg.equals("")) {
                errMsg = jsonObject.getString("errmsg");
            }
            throw new WeChatException("异常码:" + errCode + ";异常说明:" + errMsg);
        }
    }

}
