package com.wsj.wechat.api.model.query;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;

/**
 * 申请退款查询参数
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_5&index=7
 */
public class RefundQueryReqData extends CommonReqData{

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 随机字符串 必填
     */
    private String nonce_str;
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户退款单号 必填
     */
    private String out_refund_no;
    /**
     * 微信退款单号 四选一
     */
    private String refund_id;

    public RefundQueryReqData() {
    }

    public RefundQueryReqData(String out_refund_no) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.out_refund_no = out_refund_no;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }
}
