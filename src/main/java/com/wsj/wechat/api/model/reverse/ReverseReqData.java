package com.wsj.wechat.api.model.reverse;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;

/**
 * 刷卡支付撤销订单请求参数
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3
 */
public class ReverseReqData extends CommonReqData{

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

    public ReverseReqData(String out_trade_no) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.out_trade_no = out_trade_no;
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
}
