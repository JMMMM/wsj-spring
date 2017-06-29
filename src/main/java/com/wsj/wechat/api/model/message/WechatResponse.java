package com.wsj.wechat.api.model.message;

import java.util.List;

/**
 * 用于回复的基本消息类型
 */
public class WechatResponse {

	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private int ArticleCount;
	private List<item> Articles;

	public WechatResponse() {
	}

	public WechatResponse(String toUserName, String fromUserName,
			String createTime, String msgType, String content,
			int articleCount) {
		this.ToUserName = toUserName;
		this.FromUserName = fromUserName;
		this.CreateTime = createTime;
		this.MsgType = msgType;
		this.Content = content;
		this.ArticleCount = articleCount;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<item> getArticles() {
		return Articles;
	}

	public void setArticles(List<item> articles) {
		Articles = articles;
	}

}
