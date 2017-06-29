package com.wsj.wechat.api.model.query;

import com.wsj.wechat.api.model.common.CommonReqData;
import com.wsj.wechat.api.util.StringRandomGen;

/**
 * 订单查询接口参数
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
 */
public class OrderQueryReqData extends CommonReqData {

    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号与微信订单号二选一
     */
    private String out_trade_no;
    /**
     * 随机字符串，不长于32位
     * 必填
     */
    private String nonce_str;

    /**
     * 查询订单时所需最少参数
     *
     * @param out_trade_no 商户订单号
     */
    public OrderQueryReqData(String out_trade_no) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.out_trade_no = out_trade_no;
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

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

}
