package com.wsj.wechat.api.constant;

/**
 * 订单查询时 交易状态
 */
public enum TradeStatus {

    /**
     * 支付成功
     */
    SUCCESS("SUCCESS", "支付成功"),
    /**
     * 转入退款
     */
    REFUND("REFUND", "转入退款"),
    /**
     * 未支付
     */
    NOTPAY("NOTPAY", "未支付"),
    /**
     * 已关闭
     */
    CLOSED("CLOSED", "已关闭"),
    /**
     * 已撤销（刷卡支付）。
     */
    REVOKED("REVOKED", "已撤销（刷卡支付）"),
    /**
     * 用户支付中
     */
    USERPAYING("USERPAYING", "用户支付中"),
    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    PAYERROR("PAYERROR", "支付失败");

    /**
     * 退款状态
     */
    private String status;
    /**
     * 退款描述
     */
    private String desc;

    /**
     * 退款构造方法私有，外部能修改
     *
     * @param status
     * @param desc
     */
    private TradeStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 获取退款状态
     *
     * @return 退款状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 获取退款描述
     *
     * @return 退款描述
     */
    public String getDesc() {
        return desc;
    }

}
