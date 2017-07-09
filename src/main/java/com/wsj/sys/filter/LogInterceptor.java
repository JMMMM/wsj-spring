package com.wsj.sys.filter;

import com.wsj.sys.enums.SysConstants;
import com.wsj.tools.WsjTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jimmy on 2017/7/8.
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,String[]> parameters = request.getParameterMap();
        logger.info(request.getContextPath()+":"+parameters.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(response.getStatus()==500){
            logger.info("/500");
            response.sendRedirect(WsjTools.getDomainName(request)+ SysConstants.ServerError.getName());
        }else if(response.getStatus()==404){
            logger.info("/404");
            response.sendRedirect(WsjTools.getDomainName(request)+SysConstants.NotFound.getName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
