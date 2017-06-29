package com.wsj.wechat.controller;

import com.wsj.tools.WsjTools;
import com.wsj.wechat.api.SnsAPI;
import com.wsj.wechat.entity.AccessToken;
import com.wsj.wechat.tools.ValidateSignature;
import com.wsj.wechat.tools.WechatAccessToken;
import com.wsj.wechat.tools.WechatConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(value = "/thridPartLoginUrl")
    public String oauth2Authorize(HttpServletRequest request) {
        String redirectUrl = WsjTools.getDomainName(request)+"/wsj_server/wechat/userCode".replace("http","https");
        return SnsAPI.connectOauth2Authorize(WechatConfigure.getAppId(), redirectUrl, false, "123");
    }

    @RequestMapping(value = "/userCode")
    public void userCode(String code, String state) {
        logger.info(code+"======"+state);
    }
}
