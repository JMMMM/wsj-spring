package com.wsj.tools;

import com.wsj.sys.enums.SysConstants;
import com.wsj.wechat.bean.token.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jimmy on 2017/6/27.
 */
public class WsjTools {
    /**
     * 获取域名
     *
     * @param request
     * @return
     */
    public static String getDomainName(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    /**
     * 登录页面URL
     *
     * @param request
     * @return
     */
    public static String getLoginPath(HttpServletRequest request) {
        return getDomainName(request) + SysConstants.LoginPath.getName();
    }

    /**
     * 是否为AJAX请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return requestType != null && requestType.equals("XMLHttpRequest");
    }


    public static Token getWechatToken(String appid, String secret) {
        return null;
    }
}
