package com.wsj.sys.enums;

/**
 * Created by Jimmy on 2017/7/7.
 */
public enum ErrorCode {
    PHONE_ERROR(1,"手机号码错误");
    private int code;
    private String message;
    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
