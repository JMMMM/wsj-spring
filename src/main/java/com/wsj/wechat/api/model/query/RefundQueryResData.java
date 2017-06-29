package com.wsj.wechat.api.model.query;

import java.util.List;

import com.wsj.wx.api.model.common.CommonResData;

/**
 * 申请退款返回参数
 * //https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_5&index=7
 */
public class RefundQueryResData extends CommonResData {

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 订单总金额
     */
    private Integer total_fee;
    /**
     * 订单金额货币种类 CNY
     */
    private String fee_type;
    /**
     * 现金支付金额
     */
    private Integer cash_fee;
    /**
     * 退款笔数
     */
    private Integer refund_count;

    private List<RefundQueryResFee> resFeeList;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public Integer getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    public List<RefundQueryResFee> getResFeeList() {
        return resFeeList;
    }

    public void setResFeeList(List<RefundQueryResFee> resFeeList) {
        this.resFeeList = resFeeList;
    }

    @Override
    public String toString() {
        return "RefundQueryResData{" +
                "device_info='" + device_info + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", total_fee=" + total_fee +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee=" + cash_fee +
                ", refund_count=" + refund_count +
                ", resFeeList=" + resFeeList +
                '}';
    }
}
