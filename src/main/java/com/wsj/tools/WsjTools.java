package com.wsj.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jimmy on 2017/6/27.
 */
public class WsjTools {
    /**
     * 获取域名
     * @param request
     * @return
     */
    public static String getDomainName(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }
}
