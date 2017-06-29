package com.wsj.wechat.api.model.common;

/**
 * 公共请求参数
 */
public class CommonReqData {

    /**
     * 微信开放平台审核通过的应用APPID  wxd678efh567hg6787
     * 必填
     */
    private String appid;
    /**
     * 微信支付分配的商户号  1230000109
     * 必填
     */
    private String mch_id;
    /**
     * 签名
     * 必填
     */
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
