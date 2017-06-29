package com.wsj.wechat.api.model.query;

/**
 * 退款查询金额
 */
public class RefundQueryResFee {
    /**
     * 商户退款单号
     */
    private String out_refund_no;
    /**
     * 微信退款单号
     */
    private String refund_id;
    /**
     * 退款渠道
     * ORIGINAL—原路退款
     * BALANCE—退回到余额
     */
    private String refund_channel;
    /**
     * 退款金额
     */
    private Integer refund_fee;
    /**
     * 退款状态
     */
    private String refund_status;
    /**
     * 退款入账账户
     */
    private String refund_recv_accout;

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

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getRefund_recv_accout() {
        return refund_recv_accout;
    }

    public void setRefund_recv_accout(String refund_recv_accout) {
        this.refund_recv_accout = refund_recv_accout;
    }

    @Override
    public String toString() {
        return "RefundQueryResFee{" +
                "out_refund_no='" + out_refund_no + '\'' +
                ", refund_id='" + refund_id + '\'' +
                ", refund_channel='" + refund_channel + '\'' +
                ", refund_fee=" + refund_fee +
                ", refund_status='" + refund_status + '\'' +
                ", refund_recv_accout='" + refund_recv_accout + '\'' +
                '}';
    }
}
