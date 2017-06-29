package com.wsj.wechat.api.model.micropay;

import com.wsj.wx.api.model.common.CommonResData;

/**
 * 刷卡支付返回数据
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
 */
public class MicroPayResData extends CommonResData {
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 是否关注公众账号
     */
    private String is_subscribe;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 付款银行
     */
    private String bank_type;
    /**
     * 货币类型
     */
    private String fee_type;
    /**
     * 订单金额
     */
    private Integer total_fee;
    /**
     * 现金支付货币类型
     */
    private String cash_fee_type;
    /**
     * 现金支付金额
     */
    private Integer cash_fee;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商家数据包
     */
    private String attach;
    /**
     * 支付完成时间 20141030133525
     */
    private String time_end;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}
