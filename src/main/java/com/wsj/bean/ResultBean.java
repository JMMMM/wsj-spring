package com.wsj.bean;

/**
 * Created by Jimmy on 2017/6/23.
 */
public class ResultBean<T> implements java.io.Serializable {
    private boolean isSuccess;
    private String message;
    private T obj;

    public <T>ResultBean() {
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
        this.obj = obj;
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
}
