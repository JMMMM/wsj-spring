package com.wsj.wechat.controller;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.sys.bean.ResultBean;
import com.wsj.sys.enums.SysConstants;
import com.wsj.tools.CookieUtil;
import com.wsj.tools.WsjTools;
import com.wsj.wechat.api.SnsAPI;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by Jimmy on 2017/6/28.
 */
@RestController
@RequestMapping(value = "/wechat/")
public class WechatBaseController {
    @Autowired
    private WechatBaseService wechatBaseService;
    @Autowired
    private CustomerService customerService;
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

        logger.info("微信OwnerCheck:" + signature + "--" + timestamp + "--" + nonce + "--" + echostr);
        if (signature != null && ValidateSignature.checkSignature(signature, timestamp, nonce)) {
            try {
                PrintWriter print = response.getWriter();
                print.write(echostr == null ? "" : echostr);
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

    /**
     * 微信登陆授权页面
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/wxLoginUserInfoUrl")
    public void oauth2Authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = WsjTools.getDomainName(request) + "/wsj_server/wechat/userCode";
        logger.info("微信登录授权:" + redirectUrl);
        response.sendRedirect(SnsAPI.connectOauth2Authorize(WechatConfigure.getAppId(), redirectUrl, true, "wsj_checking"));
    }

//    /**
//     * 根据refresh token 刷新用户access_token
//     *
//     * @param request
//     */
//    @RequestMapping(value = "/wxLoginUserBaseUrl")
//    public void getUserAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String redirectUrl = WsjTools.getDomainName(request) + "/wsj_server/wechat/baseUserCode";
//        logger.info("微信免授权登录:" + redirectUrl);
//        response.sendRedirect(SnsAPI.connectOauth2Authorize(WechatConfigure.getAppId(), redirectUrl, false, "wsj_checking"));
//    }

//    @RequestMapping(value = "/baseUserCode")
//    public WxCustomer baseUserCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String code = request.getParameter("code");
//        String state = request.getParameter("state");
//        if (!"wsj_checking".equals(state)) return null;
//        logger.info("微信免授权登录跳转：" + code + "======" + state);
//        SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConfigure.getAppId(), WechatConfigure.getAppSecrect(), code);
//        WxCustomer wxCustomer = wechatBaseService.findWxCustomerByOpenid(snsToken.getOpenid());
//        if (wxCustomer == null) {
//            response.sendRedirect(WsjTools.getDomainName(request) + "/wsj_server/wechat/wxLoginUserInfoUrl");
//            return null;
//        } else {
//            String refreshToken = StringUtils.isEmpty(snsToken.getRefresh_token()) ? wxCustomer.getRefreshToken() : snsToken.getRefresh_token();
//            SnsToken snsToken1 = SnsAPI.oauth2RefreshToken(WechatConfigure.getAppId(), refreshToken);
//            if (!snsToken1.isSuccess()) {
//                logger.info("refresh token 过期，重定向到 wxLoginUserInfoUrl");
//                response.sendRedirect(WsjTools.getDomainName(request) + "/wsj_server/wechat/wxLoginUserInfoUrl");
//            }
//            UserInfo userInfo = SnsAPI.userinfo(snsToken1.getAccess_token(), snsToken.getOpenid(), "zh_CN");
//            return wechatBaseService.insertOrUpdateUserInfo(userInfo, snsToken1);
//        }
//
//    }

    /**
     * 用户重新授权并获取用户信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userCode")
    public void userCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info(code + "======" + state);
        SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConfigure.getAppId(), WechatConfigure.getAppSecrect(), code);
        UserInfo userInfo = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
        Cookie cookie = new Cookie(SysConstants.WsjWxOpenId.getName(), userInfo.getOpenid());
        cookie.setMaxAge(2592000);//一个月有效
        cookie.setPath("/");
        response.addCookie(cookie);
        WxCustomer exists = wechatBaseService.findWxCustomerByOpenid(snsToken.getOpenid());
        if (exists == null) {
            wechatBaseService.insertOrUpdateUserInfo(userInfo, snsToken);
            response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.WebLoginPath.getName() + "?isWechat=true");
        } else {
            WxCustomer wxCustomer = wechatBaseService.insertOrUpdateUserInfo(userInfo, snsToken);
            Customer customer = customerService.findCustomerByWxCustomerId(wxCustomer.getId());
            customerService.login(customer.getLoginName(), customer.getPassword());
            response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.WebIndexPath.getName());
        }

    }

    /**
     * 微信第三方登陆接口
     *
     * @param request
     * @param response resultBean json
     * @throws IOException
     */
    @RequestMapping(value = "/thirdPartLogin")
    public void thirdPartLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        String openId = CookieUtil.getCookieValue(cookies, SysConstants.WsjWxOpenId.getName());
        logger.info("thirdPartLogin:" + "openId->" + openId);
        WxCustomer wxCustomer = wechatBaseService.findWxCustomerByOpenid(openId);
        if (wxCustomer == null) {
            response.sendRedirect(WsjTools.getDomainName(request) + "/wsj_server/wechat/wxLoginUserInfoUrl");
        } else {
            SnsToken snsToken = SnsAPI.oauth2RefreshToken(WechatConfigure.getAppId(), wxCustomer.getRefreshToken());
            if (!snsToken.isSuccess())
                response.sendRedirect(WsjTools.getDomainName(request) + "/wsj_server/wechat/wxLoginUserInfoUrl");
            else {
                UserInfo userInfo = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
                wechatBaseService.insertOrUpdateUserInfo(userInfo, snsToken);
                Cookie cookie = new Cookie(SysConstants.WsjWxOpenId.getName(), openId);
                cookie.setMaxAge(2592000);//一个月有效
                cookie.setPath("/");
                response.addCookie(cookie);
                Customer customer = customerService.findCustomerByWxCustomerId(wxCustomer.getId());
                if (customer == null) {
                    logger.info("未注册味食家账号!");
//                    ResultBean<WxCustomer> resultBean = ResultBean.failure("未注册味食家账号!",wxCustomer);
                    response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.WebLoginPath.getName() + "?isWechat=true");
//                    out.print(new Gson().toJson(resultBean));
//                    String contentType = "application/json";
//                    response.setContentType(contentType);
//                    PrintWriter out = response.getWriter();
//                    out.print(new Gson().toJson(resultBean));
//                    out.flush();
//                    out.close();
                } else {
                    logger.info("已绑定味食家账号");
                    ResultBean resultBean = customerService.login(customer.getLoginName(), customer.getPassword());
                    if (!resultBean.isSuccess()) {
                        response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.WebLoginPath.getName() + "?isSuccess=false&message=" + URLEncoder.encode(resultBean.getMessage(), "utf-8"));
                    } else {
                        response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.WebIndexPath.getName());
                    }
//                    PrintWriter out = response.getWriter();
//                    out.print(new Gson().toJson(resultBean));
//                    out.flush();
//                    out.close();
                }

            }
        }
    }
}
