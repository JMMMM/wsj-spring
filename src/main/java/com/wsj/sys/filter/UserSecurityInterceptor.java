package com.wsj.sys.filter;

import com.wsj.sys.annotation.SessionCheck;
import com.wsj.sys.enums.SysConstants;
import com.wsj.tools.WsjTools;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by jimmy on 2017/6/25.
 */
@Component
public class UserSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SessionCheck sessionCheck = handlerMethod.getMethodAnnotation(SessionCheck.class);
        if (sessionCheck == null || !sessionCheck.checked()) return true;
        Object obj = request.getSession().getAttribute(SysConstants.LoginSession.getName());
        if (obj != null) return true;
        response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.LoginPath.getName());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
