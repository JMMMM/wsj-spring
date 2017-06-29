package com.wsj.wechat.api.exception;

/**
 * 异常处理
 */
public class WeChatException extends Exception {
	private static final long serialVersionUID = 1L;
	public WeChatException(String msg){
		super(msg);
	}
}
