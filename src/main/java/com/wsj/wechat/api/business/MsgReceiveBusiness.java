package com.wsj.wechat.api.business;

import com.iplas.sw.pojo.domain.hibernate.hbm.SysAccount;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.uap.common.utils.AppContextUtils;
import com.uap.common.utils.ObjectUtils;
import com.uap.core.service.IDaoService;
import com.wsj.wx.api.event.EventType;
import com.wsj.wx.api.event.MsgType;
import com.wsj.wx.api.model.common.SignatureParam;
import com.wsj.wx.api.model.common.WechatParamName;
import com.wsj.wx.api.model.message.WechatRequest;
import com.wsj.wx.api.model.message.WechatResponse;
import com.wsj.wx.api.model.message.item;
import com.wsj.wx.api.model.user.UserInfo;
import com.wsj.wx.api.util.ValidateSignature;
import com.wsj.wx.api.util.WeiXinConfigure;
import com.wsj.wx.api.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 公众号事件接收
 * 抽象方法中，on开头的是msgtype的事件，无on的是event事件
 */
public class MsgReceiveBusiness {
	
	Logger logger = Logger.getLogger(MsgReceiveBusiness.class);
	
	private HttpServletRequest request;
	
	protected WechatRequest wechatRequest;
	protected WechatResponse wechatResponse;
	
	/**
	 * 构建微信处理
	 * @param request   微信服务发过来的http请求
	 */
	public MsgReceiveBusiness(HttpServletRequest request){
		this.request = request;
		this.wechatRequest = new WechatRequest();
		this.wechatResponse = new WechatResponse();
	}

	/**
	 * wechat调用入口，进行数据接收，事件分发
	 * @return
	 */
	public String execute(){
		logger.debug("WechatSupport run");
		SignatureParam param = new SignatureParam(request);
		String signature =param.getSignature();
		String timestamp = param.getTimestamp();
		String nonce = param.getNonce();
		String echostr = param.getEchostr();
		String token = WeiXinConfigure.getToken();
		
		ValidateSignature validateSignature = new ValidateSignature(signature, timestamp, nonce, token);
		if(!validateSignature.check()){
			return "error";
		}
		if(StringUtils.isNotBlank(echostr)){
     		return echostr;
		}
		//分发消息，得到响应
		String result = dispatch();
		logger.info("response data:" + result);
		return result;
	}
	
	/**
	 * 分发处理，得到响应
	 * @return
	 */
	private String dispatch() {
		String postDataStr = XmlUtil.parseRequst(request);
		// 解析数据
		setPostData(postDataStr);
		// 消息分发处理
		dispatchMessage();
		// 响应事件
		String result = response();
		return result;
	}
	
	
	/**
	 * 得到post数据，对象化
	 * @param xmlStr
	 */
	private void setPostData(String xmlStr){
		logger.info("wx request post data:" + xmlStr);
		try {
			this.wechatRequest = XmlUtil.getObjectFromXML(xmlStr, WechatRequest.class);
		} catch (Exception e) {
			logger.error("post data parse error");
			e.printStackTrace();
		}
	}

	/**
	 * 消息事件分发
	 */
	private void dispatchMessage(){
		logger.info("distributeMessage start");
		if(StringUtils.isBlank(wechatRequest.getMsgType())){
			logger.info("msgType is null");
		}
		MsgType msgType = MsgType.valueOf(wechatRequest.getMsgType());
		logger.info("msgType is " + msgType.name());
		switch (msgType) {
		case event:
			dispatchEvent();
			break;
		case text:
			onText();
			break;
		case image:
			onImage();
			break;
		case voice:
			onVoice();
			break;
		case video:
			onVideo();
			break;
		case shortvideo:
			onShortVideo();
		case location:
			onLocation();
			break;
		case link:
			onLink();
			break;
		default:
			onUnknown();
			break;
		}
	}
	
	/**
	 * event事件分发
	 */
	private void dispatchEvent() {
		EventType event = EventType.valueOf(wechatRequest.getEvent());
		logger.info("dispatch event,event is " + event.name());
		switch (event) {
		case CLICK:
			click();
			break;
		case subscribe:
			subscribe();
			break;
		case unsubscribe:
			unSubscribe();
			break;
		case SCAN:
			scan();
			break;
		case LOCATION:
			location();
			break;
		case VIEW:
			view();
			break;
		case TEMPLATESENDJOBFINISH:
			templateMsgCallback();
			break;
		case scancode_push:
			scanCodePush();
			break;
		case scancode_waitmsg:
			scanCodeWaitMsg();
		    break;
		case pic_sysphoto:
			picSysPhoto();
		    break;
		case pic_photo_or_album:
			picPhotoOrAlbum();
		    break;
		case pic_weixin:
			picWeixin();
		    break;
		case location_select:
			locationSelect();
		    break;
		case kf_create_session:
			kfCreateSession();
			break;
		case kf_close_session:
			kfCloseSession();
			break;
		case kf_switch_session:
			kfSwitchSession();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 返回响应数据
	 * @return
	 */
	private String response(){
		String result = null;
		try {
	        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	        xStream.alias("xml", wechatResponse.getClass()); 
	        xStream.alias("item", item.class); 
	        result = xStream.toXML(wechatResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 响应数据基础构造
	 */
	private void responseBase(){
		wechatResponse.setToUserName(this.wechatRequest.getFromUserName());
		wechatResponse.setFromUserName(wechatRequest.getToUserName());
		wechatResponse.setCreateTime(wechatRequest.getCreateTime());
	}
	
	/**
	 * 回复文本消息
	 * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 */
	public void responseText(String content){
		responseBase();
		wechatResponse.setMsgType(MsgType.text.name());
		wechatResponse.setContent(content);
	}
	
	/**
	 * 回复图文消息
	 * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 */
	public void responseArticles(){
		responseBase();
		
		List<item> articles = new ArrayList<item>();
		item article = new item();
		article.setTitle("注册赢iPhone7！还来Chinaplas找技术?百位行家已在塑问恭候您！");
		article.setDescription("活动时间：2017年5月15日--6月15日活动期间注册塑问，即有机会抽取iPhone7更有千元红包等你抢！");
		//塑问塑答
		//article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/7O9Rta048tRNU8n9szZPm9naAVEmfmgcCZBtDyKDia1Gw1dk0n8xsGstczq87cO6k1vslmbcBhPichib9uqNS6NQQ/0?wx_fmt=png");
		//article.setUrl("https://mp.weixin.qq.com/s?__biz=MzI2MjYwNjA3Mw==&mid=100000009&idx=1&sn=5b487c211d057f713c34534b52c6a61d&chksm=6a49dc9f5d3e5589a5af157af1ac63ed8871e05b189ba28a8093ec0dec442c90fb9ac203f196#rd");
		
		//塑问在线
		article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/d9DaCRTTSgQ3iaUv9wcja2CErP7uKYibVIm499TRj9HI45VYnAZZTgVDEjibbq2Jf25iaiaicfXPibaYbPXmqXBdoGDaQ/0?wx_fmt=png");
		article.setUrl("https://mp.weixin.qq.com/s?__biz=MzIyOTgwMDU5Ng==&mid=100000004&idx=1&sn=9d4824b81038c64fd0feac9eddfc869b&chksm=68bc6eff5fcbe7e9c2a4c44d036d5af5cb33a71edd68320f2324f16f9e86943edab0c14db813#rd");
		articles.add(article);
		
		wechatResponse.setMsgType(MsgType.news.name());
		wechatResponse.setArticleCount(articles.size());
		wechatResponse.setArticles(articles);
	}
	
	/**
	 * 文本消息处理Msgtype=text
	 */
	private void onText() {
//		if("抽奖".equals(wechatRequest.getContent())){
//			responseArticles();
//		} else {
//		}
		responseText(WechatParamName.RESPONSE_INPUT_CONTENT);
	}

	/**
	 * 图像消息Msgtype=image
	 */
	private void onImage() {
		responseText(WechatParamName.RESPONSE_INPUT_CONTENT);
	}

	/**
	 * 语音消息 Msgtype=voice
	 */
	private void onVoice() {
		responseText(WechatParamName.RESPONSE_INPUT_CONTENT);
	}

	/**
	 * 视频 消息Msgtype=video
	 */
	private void onVideo() {
		responseText(WechatParamName.RESPONSE_INPUT_CONTENT);
	}

	/**
	 * 小视频 消息Msgtype=shortvideo
	 */
	private void onShortVideo() {
		responseText(WechatParamName.RESPONSE_INPUT_CONTENT);
	}

	/**
	 * 地理位置消息Msgtype=location
	 */
	private void onLocation() {
	}

	/**
	 * 链接消息Msgtype=link
	 */
	private void onLink() {
	}

	/**
	 * 未知消息类型的错误处理逻辑，不需要处理则空方法即可
	 */
	private void onUnknown() {
	}

	/**
	 * click点击事件处理event=location
	 */
	private void click() {
	}

	/**
	 * subscribe关注事件处理
	 */
	private void subscribe() {
		responseText(WechatParamName.WELCOME_CONTENT);
		
		String eventKey = wechatRequest.getEventKey();
		if(ObjectUtils.isNotNull(eventKey) && ObjectUtils.isNotNull(wechatRequest.getFromUserName())){
			IDaoService daoService = (IDaoService) AppContextUtils.getBean(IDaoService.class.getName());
			List<SysAccount> bindAccounts = (List<SysAccount>) daoService.findByPropertyName(SysAccount.class, "wxOpenId", wechatRequest.getFromUserName());
			if(ObjectUtils.isNotNull(bindAccounts)){
				logger.error("绑定失败,微信号:"+wechatRequest.getFromUserName()+",已被绑定,不能重复绑定");
				return;
			}
			
			String userId = eventKey.split("_")[1];
			List<SysAccount> accounts = (List<SysAccount>) daoService.findByPropertyName(SysAccount.class, "userId", userId);
			if(ObjectUtils.isNotNull(accounts)){
				SysAccount sysAccount = accounts.get(0);
				if(ObjectUtils.isNull(sysAccount.getWxNo())){
					//获取关注微信用户基本信息
					UserBusiness userBusiness = new UserBusiness();
					UserInfo user = userBusiness.getUserInfo(wechatRequest.getFromUserName());
					if(ObjectUtils.isNotNull(user)){
						if(ObjectUtils.isNotNull(user.getUnionId())){
							sysAccount.setWxNo(user.getUnionId());
						} else {
							sysAccount.setWxNo(user.getNickName());
						}
						sysAccount.setWxNickName(user.getNickName());
						sysAccount.setWxHeadImgUrl(user.getHeadimgUrl());
						sysAccount.setWxOpenId(wechatRequest.getFromUserName());
						daoService.updateEntity(sysAccount);
					}
				} else {
					logger.error("用户:"+userId+",已绑定微信号"+sysAccount.getWxNo());
				}
			} else {
				logger.error("用户:"+userId+",账号不存在");
			}
		}
	}

	/**
	 * unSubscribe取消关注事件处理
	 */
	private void unSubscribe() {
		responseBase();
		
		if(ObjectUtils.isNotNull(wechatRequest.getFromUserName())){
			IDaoService daoService = (IDaoService) AppContextUtils.getBean(IDaoService.class.getName());
			List<SysAccount> bindAccounts = (List<SysAccount>) daoService.findByPropertyName(SysAccount.class, "wxOpenId", wechatRequest.getFromUserName());
			if(ObjectUtils.isNotNull(bindAccounts)){
				SysAccount sysAccount = bindAccounts.get(0);
				logger.info("用户 "+sysAccount.getUserId()+" 取消关注公众号");
				sysAccount.setWxNo("");
				sysAccount.setWxNickName("");
				sysAccount.setWxHeadImgUrl("");
				sysAccount.setWxOpenId("");
				daoService.updateEntity(sysAccount);
				return;
			}
		}
	}

	/**
	 * scan事件处理
	 */
	private void scan() {
		responseText(WechatParamName.RESPONSE_CONTENT);
		
		String eventKey = wechatRequest.getEventKey();
		if(ObjectUtils.isNotNull(eventKey) && ObjectUtils.isNotNull(wechatRequest.getFromUserName())){
			IDaoService daoService = (IDaoService) AppContextUtils.getBean(IDaoService.class.getName());
			List<SysAccount> bindAccounts = (List<SysAccount>) daoService.findByPropertyName(SysAccount.class, "wxOpenId", wechatRequest.getFromUserName());
			if(ObjectUtils.isNotNull(bindAccounts)){
				logger.error("绑定失败,微信号:"+wechatRequest.getFromUserName()+",已被绑定,不能重复绑定");
				return;
			}
			
			String userId = eventKey;
			List<SysAccount> accounts = (List<SysAccount>) daoService.findByPropertyName(SysAccount.class, "userId", userId);
			if(ObjectUtils.isNotNull(accounts)){
				SysAccount sysAccount = accounts.get(0);
				if(ObjectUtils.isNull(sysAccount.getWxOpenId())){
					//获取关注微信用户基本信息
					UserBusiness userBusiness = new UserBusiness();
					UserInfo user = userBusiness.getUserInfo(wechatRequest.getFromUserName());
					if(ObjectUtils.isNotNull(user)){
						if(ObjectUtils.isNotNull(user.getUnionId())){
							sysAccount.setWxNo(user.getUnionId());
						} else {
							sysAccount.setWxNo(user.getNickName());
						}
						sysAccount.setWxNickName(user.getNickName());
						sysAccount.setWxHeadImgUrl(user.getHeadimgUrl());
						sysAccount.setWxOpenId(wechatRequest.getFromUserName());
						daoService.updateEntity(sysAccount);
					}
				} else {
					logger.error("用户:"+userId+",已绑定微信号"+sysAccount.getWxNo());
				}
			} else {
				logger.error("用户:"+userId+",账号不存在");
			}
		}
	}

	/**
	 * location事件处理event=location
	 */
	private void location() {
	}

	/**
	 * view 事件处理event=location
	 */
	private void view() {
	}

	/**
	 * 模板消息发送回调
	 */
	private void templateMsgCallback() {
	}

	/**
	 * 扫码推事件
	 */
	private void scanCodePush() {
	}

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	private void scanCodeWaitMsg() {
	}

	/**
	 * 弹出系统拍照发图的事件
	 */
	private void picSysPhoto() {
	}

	/**
	 * 弹出拍照或者相册发图的事件
	 */
	private void picPhotoOrAlbum() {
	}

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	private void picWeixin() {
	}

	/**
	 * 弹出地理位置选择器的事件
	 */
	private void locationSelect() {
	}

	/**
	 * 客服人员有接入会话
	 */
	private void kfCreateSession() {
	}

	/**
	 * 客服人员有关闭会话
	 */
	private void kfCloseSession() {
	}

	/**
	 * 客服人员有转接会话
	 */
	private void kfSwitchSession() {
	}
}
