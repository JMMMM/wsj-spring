package com.wsj.wechat.controller;

import com.wsj.wechat.api.SnsAPI;
import com.wsj.wechat.bean.user.UserInfo;
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

    @RequestMapping(value = "accessToken")
    public AccessToken accessToken() {
        return WechatAccessToken.getAccessToken();
    }

    @RequestMapping(value = "/login")
    public String oauth2Authorize() {
        return SnsAPI.connectOauth2Authorize(WechatConfigure.getAppId(),"https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdap\n" +
                "ter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60",true,"123");
    }
}
