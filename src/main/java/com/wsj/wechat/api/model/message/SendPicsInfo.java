package com.wsj.wechat.api.model.message;

import javax.xml.bind.annotation.XmlElement;

public class SendPicsInfo {
	private String Count;
	
	@XmlElement(name="Count")
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}

	
	
	
}
