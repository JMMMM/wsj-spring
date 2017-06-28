package com.wsj.tools;

import com.wsj.sys.enums.SysConstants;

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

    public static String getLoginPath(HttpServletRequest request) {
        return request.getContextPath() + SysConstants.LoginPath.getName();
    }


    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return requestType != null && requestType.equals("XMLHttpRequest");
    }
}
