package com.wsj.wechat.api.model.transfers;

import com.wsj.wx.api.model.common.CommonResData;

public class TransfersResData extends CommonResData {

	/** 商户appid */
	private String mch_appid;
	/** 设备号 */
	private String device_info;
	/** 商户订单号 */
	private String partner_trade_no;
	/** 微信订单号 */
	private String payment_no;
	/** 微信支付成功时间 */
	private String payment_time;
	
	
	public String getMch_appid() {
		return mch_appid;
	}
	public void setMch_appid(String mch_appid) {
		this.mch_appid = mch_appid;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getPartner_trade_no() {
		return partner_trade_no;
	}
	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}
	public String getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}
	public String getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	
}
