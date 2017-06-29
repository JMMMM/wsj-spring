package com.wsj.wechat.api.model.prepay;

import com.wsj.wx.api.model.common.CommonResData;

/**
 * 预支付应答数据
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
 */
public class PrePayResData extends CommonResData{
    /**
     * 设备号
     */
    private String device_info;
    //以下字段在return_code 和result_code都为SUCCESS的时候有返回
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 预支付交易会话标识
     */
    private String prepay_id;

    /**
     * 扫码支付返回url地址
     */
    private String code_url;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}

