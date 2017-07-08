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
import java.io.PrintWriter;


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
        Object obj ;
        if (sessionCheck.checkedType() == SessionCheck.Type.MANAGER) {
            obj = request.getSession().getAttribute(SysConstants.ManagerLoginSession.getName());
        } else {
            obj = request.getSession().getAttribute(SysConstants.WebLoginSession.getName());
        }
        if (obj != null) return true;
        if (WsjTools.isAjaxRequest(request)) {
            if(sessionCheck.checkedType() == SessionCheck.Type.MANAGER){
                String jsonObject = "{\"sessionStatus\":\"timeout\",\"redirectHref\":"
                        + "\"" + WsjTools.getLoginPath(request) + "\"," +
                        "\"success\":false}";
                String contentType = "application/json";
                response.setContentType(contentType);
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
                out.flush();
                out.close();
                return false;
            }else{
                String jsonObject = "{\"sessionStatus\":\"timeout\",\"redirectHref\":"
                        + "\"" + WsjTools.getWebLoginPath(request) + "\"," +
                        "\"success\":false}";
                String contentType = "application/json";
                response.setContentType(contentType);
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
                out.flush();
                out.close();
                return false;
            }

        }

        response.sendRedirect(WsjTools.getLoginPath(request));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
