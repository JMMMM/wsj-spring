package com.wsj.wechat.api.model.reverse;

import com.wsj.wx.api.model.common.CommonResData;

/**
 * 刷卡支付撤销订单返回参数
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3
 */
public class ReverseResData extends CommonResData{

    /**
     * 是否重调 Y-需要，N-不需要
     */
    private String recall;

    public String getRecall() {
        return recall;
    }

    public void setRecall(String recall) {
        this.recall = recall;
    }
}
