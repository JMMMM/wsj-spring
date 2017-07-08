package com.wsj.tools;

import com.google.gson.Gson;
import com.wsj.sys.enums.SysConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jimmy on 2017/6/27.
 */
public class WsjTools {
    private static Gson gson = new Gson();

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
    public static String getWebLoginPath(HttpServletRequest request){
        return getDomainName(request) + SysConstants.WebLoginPath.getName();
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

    /**
     * json解析
     */
    public static <T> T jsonParser(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

}
