package com.wsj.sys.bean;

import com.wsj.sys.enums.ErrorCode;

/**
 * Created by Jimmy on 2017/6/23.
 */
public class ResultBean<T> {
    private boolean isSuccess;
    private String message;
    private T bean;
    private ErrorCode errorCode;
    public <T> ResultBean() {
        super();
    }

    public ResultBean(boolean isSuccess, String message) {
        super();
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ResultBean(boolean isSuccess, String message, T obj) {
        super();
        this.isSuccess = isSuccess;
        this.message = message;
        this.bean = obj;
    }
    public ResultBean(boolean isSuccess,String message,T obj,ErrorCode errorCode){
        this(isSuccess,message,obj);
        this.errorCode = errorCode;
    }
    public static <T> ResultBean failure(String message) {
        return failure(message, null);
    }

    public static <T> ResultBean success(String message) {
        return success(message, null);
    }

    public static <T> ResultBean failure(String message, T obj) {
        return new ResultBean<T>(false, message, obj);
    }

    public static <T> ResultBean success(String message, T obj) {
        return new ResultBean<T>(true, message, obj);
    }

    public static <T> ResultBean failure(String message,T obj,ErrorCode errorCode){
        return new ResultBean<T>(false,message,obj,errorCode);
    }
    public static <T> ResultBean failure(String message,ErrorCode errorCode){
        return new ResultBean<T>(false,message,null,errorCode);
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
