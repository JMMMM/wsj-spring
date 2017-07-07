package com.wsj.sys.enums;

/**
 * Created by Jimmy on 2017/7/7.
 */
public enum ErrorCode {
    SUCCESS(0,"成功"),
    PHONE_ERROR(1,"手机号码错误"),
    PASSWORD_ERROR(2,"密码长度不符合！"),
    LOGINNAME_EXISTS(3,"登陆账号已经存在"),
    USERNAME_ERROR(4,"用户名长度不符");
    private int code;
    private String message;
    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
