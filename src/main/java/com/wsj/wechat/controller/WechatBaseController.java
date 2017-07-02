package com.wsj.wechat.controller;

import com.wsj.tools.WsjTools;
import com.wsj.wechat.api.SnsAPI;
import com.wsj.wechat.api.WechatUserInfoApi;
import com.wsj.wechat.bean.sns.SnsToken;
import com.wsj.wechat.bean.user.UserInfo;
import com.wsj.wechat.entity.AccessToken;
import com.wsj.wechat.entity.WxCustomer;
import com.wsj.wechat.services.WechatBaseService;
import com.wsj.wechat.tools.ValidateSignature;
import com.wsj.wechat.tools.WechatAccessToken;
import com.wsj.wechat.tools.WechatConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jimmy on 2017/6/28.
 */
@RestController
@RequestMapping(value = "/wechat/")
public class WechatBaseController {
    @Autowired
    private WechatBaseService wechatBaseService;
    private static Logger logger = LoggerFactory.getLogger(WechatBaseController.class);

    @RequestMapping(value = "/ownerCheck", method = RequestMethod.GET)
    public void ownerCheck(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        logger.info("微信OwnerCheck:" + signature + "--" + timestamp + "--" + nonce + "--" + nonce);
        if (signature != null && ValidateSignature.checkSignature(signature, timestamp, nonce)) {
            try {
                PrintWriter print = response.getWriter();
                print.write(echostr);
                print.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/accessToken")
    public AccessToken accessToken() {
        return WechatAccessToken.getAccessToken();
    }

    @RequestMapping(value = "/wxLoginUserInfoUrl")
    public void oauth2Authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = WsjTools.getDomainName(request) + "/wsj_server/wechat/userCode";
        logger.info(redirectUrl.replace("http", "https"));
        response.sendRedirect(SnsAPI.connectOauth2Authorize(WechatConfigure.getAppId(), redirectUrl.replace("http", "https"), true, "wsj_checking"));
    }

    /**
     * 根据refresh token 刷新用户access_token
     *
     * @param request
     */
    @RequestMapping(value = "/wxLoginUserBaseUrl")
    public void getUserAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = WsjTools.getDomainName(request) + "/wsj_server/wechat/baseUserCode";
        logger.info(redirectUrl.replace("http", "https"));
        response.sendRedirect(SnsAPI.connectOauth2Authorize(WechatConfigure.getAppId(), redirectUrl.replace("http", "https"), false, "wsj_checking"));
    }

    @RequestMapping(value = "/baseUserCode")
    public WxCustomer baseUserCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (!"wsj_checking".equals(state)) return null;
        logger.info(code + "======" + state);
        SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConfigure.getAppId(), WechatConfigure.getAppSecrect(), code);
        WxCustomer wxCustomer = wechatBaseService.findWxCustomerByOpenid(snsToken.getOpenid());
        if (wxCustomer == null) {
            response.sendRedirect(WsjTools.getDomainName(request) + "/wsj_server/wechat/wxLoginUserInfoUrl");
            return null;
        } else {
            String refreshToken = StringUtils.isEmpty(snsToken.getRefresh_token())?wxCustomer.getRefreshToken():snsToken.getRefresh_token();
            SnsToken snsToken1 = SnsAPI.oauth2RefreshToken(WechatConfigure.getAppId(), refreshToken);
            UserInfo userInfo = SnsAPI.userinfo(snsToken1.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            return wechatBaseService.insertOrUpdateUserInfo(userInfo,snsToken1);
        }

    }

    /**
     * 用户重新授权并获取用户信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userCode")
    public WxCustomer userCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info(code + "======" + state);
        SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConfigure.getAppId(), WechatConfigure.getAppSecrect(), code);
        UserInfo userInfo = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
        return wechatBaseService.insertOrUpdateUserInfo(userInfo,snsToken);
    }

}
