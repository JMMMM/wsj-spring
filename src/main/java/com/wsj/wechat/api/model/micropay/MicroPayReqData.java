package com.wsj.wechat.api.model.micropay;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;

/**
 * 刷卡支付请求参数
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
 */
public class MicroPayReqData extends CommonReqData {

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 商品描述 必填
     */
    private String body;
    /**
     * 商品描述
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号 必填
     */
    private String out_trade_no;
    /**
     * 订单金额 必填
     */
    private Integer total_fee;
    /**
     * 货币类型
     */
    private String fee_type;
    /**
     * 调用微信支付API的机器IP 必填
     */
    private String spbill_create_ip;
    /**
     * 商品标记
     */
    private String goods_tag;
    /**
     * 授权码 必填
     */
    private String auth_code;

    public MicroPayReqData() {
    }

    /**
     * 构造方法所需最少参数
     *
     * @param body             商品描述
     * @param out_trade_no     商户订单号
     * @param total_fee        总金额
     * @param spbill_create_ip 服务器端ip
     * @param auth_code        授权码
     */
    public MicroPayReqData(String body, String out_trade_no, Integer total_fee, String spbill_create_ip, String auth_code) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.auth_code = auth_code;
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

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }
}
