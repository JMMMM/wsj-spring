package com.wsj.wechat.api.model.refund;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;

/**
 * 退款申请请求参数
 */
public class RefundReqData extends CommonReqData {

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
     * 商户订单号与微信订单号二选一
     */
    private String out_trade_no;
    /**
     * 商户退款单号 必填
     */
    private String out_refund_no;
    /**
     * 总金额 必填
     */
    private Integer total_fee;
    /**
     * 退款金额 必填
     */
    private Integer refund_fee;
    /**
     * 货币种类 默认CNY
     */
    private String refund_fee_type = "CNY";
    /**
     * 操作员 操作员帐号, 默认为商户号
     */
    private String op_user_id;

    /**
     * 构造方法
     *
     * @param out_trade_no  商户订单号
     * @param out_refund_no 商户退款单号
     * @param total_fee     总金额
     * @param refund_fee    退款金额
     */
    public RefundReqData(String out_trade_no, String out_refund_no, Integer total_fee, Integer refund_fee) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.out_trade_no = out_trade_no;
        this.out_refund_no = out_refund_no;
        this.total_fee = total_fee;
        this.refund_fee = refund_fee;
    }

    /**
     * 构造方法
     *
     * @param out_trade_no  商户订单号
     * @param out_refund_no 商户退款单号
     * @param total_fee     总金额
     * @param refund_fee    退款金额
     * @param op_user_id    操作员帐号, 默认为商户号
     */
    public RefundReqData(String out_trade_no, String out_refund_no, Integer total_fee, Integer refund_fee, String op_user_id) {
        this(out_trade_no, out_refund_no, total_fee, refund_fee);
        this.op_user_id = op_user_id;
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

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }
}
