package com.wsj.wechat.api.model.close;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;

/**
 * 关闭订单请求参数
 * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
 * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_3&index=5
 */
public class CloseOrderReqData extends CommonReqData{

    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 随机字符串，不长于32位
     * 必填
     */
    private String nonce_str;

    public CloseOrderReqData(String out_trade_no) {
        //随机字符串，不长于32 位
        setNonce_str(StringRandomGen.getRandomStringByLength(32));
        this.out_trade_no = out_trade_no;
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
