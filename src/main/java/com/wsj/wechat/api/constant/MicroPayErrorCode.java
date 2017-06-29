package com.wsj.wechat.api.constant;

/**
 * 扫码支付错误码
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
 */
public enum MicroPayErrorCode {

    /**
     * 支付结果未知
     * 系统超时
     * 请立即调用被扫订单结果查询API，查询当前订单状态，并根据订单的状态决定下一步的操作。
     */
    SYSTEMERROR("SYSTEMERROR", "接口返回错误"),
    /**
     * 支付确认失败
     * 请求参数未按指引进行填写
     * 请根据接口返回的详细信息检查您的程序
     */
    PARAM_ERROR("PARAM_ERROR", "参数错误"),
    /**
     * 支付确认失败
     * 订单号重复
     * 请确认该订单号是否重复支付，如果是新单，请使用新订单号提交
     */
    ORDERPAID("ORDERPAID", "订单已支付"),
    /**
     * 支付确认失败
     * 商户没有开通被扫支付权限
     * 请开通商户号权限。请联系产品或商务申请
     */
    NOAUTH("NOAUTH", "商户无权限"),
    /**
     * 支付确认失败
     * 用户的零钱余额不足
     * 请收银员提示用户更换当前支付的卡，然后请收银员重新扫码。建议：商户系统返回给收银台的提示为“用户余额不足.提示用户换卡支付”
     */
    NOTENOUGH("NOTENOUGH", "余额不足"),
    /**
     * 支付确认失败
     * 用户使用卡种不支持当前支付形式
     * 请用户重新选择卡种 建议：商户系统返回给收银台的提示为“该卡不支持当前支付，提示用户换卡支付或绑新卡支付”
     */
    NOTSUPORTCARD("NOTSUPORTCARD", "不支持卡类型"),
    /**
     * 支付确认失败
     * 该订单已关
     * 商户订单号异常，请重新下单支付
     */
    ORDERCLOSED("ORDERCLOSED", "订单已关闭"),
    /**
     * 支付确认失败
     * 当前订单已经被撤销
     * 当前订单状态为“订单已撤销”，请提示用户重新支付
     */
    ORDERREVERSED("ORDERREVERSED", "订单已撤销"),
    /**
     * 支付结果未知
     * 银行端超时
     * 请立即调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作。
     */
    BANKERROR("BANKERROR", "银行系统异常"),
    /**
     * 支付结果未知
     * 该笔交易因为业务规则要求，需要用户输入支付密码。
     * 等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作。
     */
    USERPAYING("USERPAYING", "用户支付中，需要输入密码"),
    /**
     * 支付确认失败
     * 请求参数未按指引进行填写
     * 每个二维码仅限使用一次，请刷新再试
     */
    AUTH_CODE_ERROR("AUTH_CODE_ERROR", "授权码参数错误"),
    /**
     * 支付确认失败
     * 收银员扫描的不是微信支付的条码
     * 请扫描微信支付被扫条码/二维码
     */
    AUTH_CODE_INVALID("AUTH_CODE_INVALID","授权码检验错误");

    /**
     * 退款状态
     */
    private String errorCode;
    /**
     * 退款描述
     */
    private String desc;

    /**
     * 扫码支付错误码构造方法私有，外部能修改
     *
     * @param errorCode 错误码
     * @param desc      描述
     */
    private MicroPayErrorCode(String errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
    }

    /**
     * 获取退款状态
     *
     * @return 退款状态
     */
    public String getErrorCode() {
        return errorCode;
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
