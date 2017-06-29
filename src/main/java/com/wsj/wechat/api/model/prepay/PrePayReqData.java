package com.wsj.wechat.api.model.prepay;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;

/**
 * 预支付请求参数
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
 */
public class PrePayReqData extends CommonReqData {
    /**
     * 终端设备号(门店号或收银设备ID)，默认请传"WEB"  013467007045764
     */
    private String device_info;
    /**
     * 随机字符串，不长于32位
     * 必填
     */
    private String nonce_str;
    /**
     * 商品描述 商品或支付单简要描述
     * 必填
     */
    private String body;
    /**
     * 商品详情 商品名称明细列表
     */
    private String detail;
    /**
     * 附加数据 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;
    /**
     * 商户订单号　商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     * 必填
     */
    private String out_trade_no;
    /**
     * 货币类型 CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String fee_type;
    /**
     * 总金额 订单总金额，单位为分
     * 必填
     */
    private Integer total_fee;
    /**
     * 终端ip 用户端实际ip
     * 必填
     */
    private String spbill_create_ip;
    /**
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String time_start;
    /**
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010
     */
    private String time_expire;
    /**
     * 商品标记 商品标记，代金券或立减优惠功能的参数
     */
    private String goods_tag;
    /**
     * 通知地址
     * 必填
     */
    private String notify_url;
    /**
     * 交易类型 APP
     * 必填
     *
     * @see com.weixin.pay.WeiXinPayType
     */
    private String trade_type;
    /**
     * 指定支付方式 no_credit--指定不能使用信用卡支付
     */
    private String limit_pay;
    /**
     * trade_type=JSAPI时（即公众号支付），此参数必传，微信用户在商户对应appid下的唯一标识
     */
    private String openid;

    public PrePayReqData() {
    }

    /**
     * 预支付时所需最少参数
     *
     * @param body             商品描述
     * @param out_trade_no     商户订单号
     * @param total_fee        订单总金额，单位为分
     * @param spbill_create_ip 用户端实际ip
     * @param notify_url       通知地址
     * @param trade_type       交易类型
     */
    public PrePayReqData(String body, String out_trade_no, Integer total_fee, String trade_type) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.trade_type = trade_type;
    }

    /**
     * 预支付时所需最少参数
     *
     * @param body             商品描述
     * @param out_trade_no     商户订单号
     * @param total_fee        订单总金额，单位为分
     * @param spbill_create_ip 用户端实际ip
     * @param notify_url       通知地址
     * @param trade_type       交易类型
     * @param time_expire      订单过期时间
     */
    public PrePayReqData(String body, String out_trade_no, Integer total_fee, String trade_type, String time_expire) {
        this(body, out_trade_no, total_fee, trade_type);
        this.time_expire = time_expire;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
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

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
    
}
