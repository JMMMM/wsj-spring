package com.wsj.wechat.api.model.redpack;

import com.wsj.wx.api.model.common.CommonReqData;
import com.wsj.wx.api.util.StringRandomGen;


public class RedpackReqData extends CommonReqData {

	/** 随机字符串 */
	private String nonce_str;
	/**
	 * 签名 必填
	 */
	private String sign;
	/** 商户订单号，需保持唯一性 */
	private String mch_billno;
	/** 商户号 */
	private String mch_id;
	/** 公众账号appid */
	private String wxappid;
	/** 商户名称，红包发送者名称  */
	private String send_name;	
	/** 用户openid */
	private String re_openid;
	/** 付款金额，单位为分 */
	private int total_amount;
	/** 红包发放总人数 */
	private int total_num;	
	/** 红包祝福语 */
	private String wishing;
	/** Ip地址,调用接口的机器Ip地址 */
	private String client_ip;
	/** 活动名称,如猜灯谜抢红包活动 */
	private String act_name;
	/** 备注,如猜越多得越多，快来抢！*/
	private String remark;
	/** 场景id，发放红包使用场景，红包金额大于200时必传 */
	private String scene_id;

	public RedpackReqData() {
	}

	public RedpackReqData(String mch_billno, String send_name, String re_openid,
			int total_amount, int total_num, String wishing, String client_ip,
			String act_name, String remark) {
		setNonce_str(StringRandomGen.getRandomStringByLength(32));
		this.mch_billno = mch_billno;
		this.send_name = send_name;
		this.re_openid = re_openid;
		this.total_amount = total_amount;
		this.total_num = total_num;
		this.wishing = wishing;
		this.client_ip = client_ip;
		this.act_name = act_name;
		this.remark = remark;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	
}
