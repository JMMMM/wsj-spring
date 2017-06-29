package com.wsj.wechat.api.model.common;

import com.wsj.wx.api.constant.WeiXinResultStatus;

/**
 * 请求结果
 *
 * @param <E> 结果返回
 */
public class WeiXinResult<E> {
    /**
     * 请求状态
     *
     * @see WeiXinResultStatus
     */
    private Integer status;
    /**
     * 消息提示
     */
    private String message;
    /**
     * 返回结果
     */
    private E infobean;

    public WeiXinResult(Integer status, String message, E infobean) {
        this.status = status;
        this.message = message;
        this.infobean = infobean;
    }

    public WeiXinResult() {
    }

    public E getInfobean() {
        return infobean;
    }

    public void setInfobean(E infobean) {
        this.infobean = infobean;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return status == WeiXinResultStatus.Success.code;
    }

    public boolean isFailure() {
        return status == WeiXinResultStatus.Failure.code;
    }

    public boolean isError() {
        return status == WeiXinResultStatus.Error.code;
    }

    public static <T> WeiXinResult<T> success() {
        return success(null, null);
    }

    public static <T> WeiXinResult<T> success(String message) {
        return success(message, null);
    }

    public static <T> WeiXinResult<T> success(T infobean) {
        return success(null, infobean);
    }

    public static <T> WeiXinResult<T> success(String message, T infobean) {
        return new WeiXinResult(WeiXinResultStatus.Success.code, message, infobean);
    }

    public static <T> WeiXinResult<T> failure(String message) {
        return failure(message, null);
    }

    public static <T> WeiXinResult<T> failure(String message, T infobean) {
        return new WeiXinResult(WeiXinResultStatus.Failure.code, message, infobean);
    }

    private static final String ERROR_MESSAGE_TEMPLATE =
            "时发生异常，请检查内容是否填写正常后重试，如再次失败请联系客服或技术人员，谢谢！";

    public static <T> WeiXinResult<T> error(String moduleName, Exception e) {
        return new WeiXinResult(WeiXinResultStatus.Error.code,
                moduleName + ERROR_MESSAGE_TEMPLATE, null);
    }

    public static <T> WeiXinResult<T> error(String message) {
        return error(message, null);
    }

    public static <T> WeiXinResult<T> error(String message, T infobean) {
        return new WeiXinResult(WeiXinResultStatus.Error.code, message, infobean);
    }

    public static <T> WeiXinResult<T> set(Integer status, String message, T infobean) {
        return new WeiXinResult(status, message, infobean);
    }

    public static <T> WeiXinResult<T> valueOf(WeiXinResult result) {
        return set(result.getStatus(), result.getMessage(), null);
    }

}
