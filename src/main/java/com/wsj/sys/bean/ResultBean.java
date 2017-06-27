package com.wsj.sys.bean;

/**
 * Created by Jimmy on 2017/6/23.
 */
public class ResultBean<T> {
    private boolean isSuccess;
    private String message;
    private T bean;

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
}
