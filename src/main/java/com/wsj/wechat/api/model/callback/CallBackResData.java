package com.wsj.wechat.api.model.callback;

/**
 * App支付回调返回给微信端参数
 */
public class CallBackResData {

    private String return_code;
    private String return_msg;

    public CallBackResData(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
