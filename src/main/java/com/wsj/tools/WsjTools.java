package com.wsj.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jimmy on 2017/6/27.
 */
public class WsjTools {
    public static String getDomainName(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
    }
}
