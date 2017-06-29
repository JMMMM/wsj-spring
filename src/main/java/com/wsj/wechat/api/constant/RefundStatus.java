package com.wsj.wechat.api.constant;

/**
 * 退款状态
 */
public enum RefundStatus {

    /**
     * 退款成功
     */
    SUCCESS("SUCCESS", "退款成功"),
    /**
     * 退款失败
     */
    FAIL("FAIL", "退款失败"),
    /**
     * 退款处理中
     */
    PROCESSING("PROCESSING", "退款处理中"),
    /**
     * 需要商户原退款单号重新发起
     */
    NOTSURE("NOTSURE", "未确定"),
    /**
     * 退款到银行发现用户的卡作废或者冻结了，
     * 导致原路退款银行卡失败，
     * 资金回流到商户的现金帐号，
     * 需要商户人工干预，
     * 通过线下或者财付通转账的方式进行退款。
     */
    CHANGE("CHANGE", "转入代发");

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
    private RefundStatus(String status, String desc) {
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
