package com.wsj.manager.staffs.controller;

import com.wsj.manager.staffs.entity.Staff;
import com.wsj.manager.staffs.services.StaffService;
import com.wsj.sys.annotation.SessionCheck;
import com.wsj.sys.bean.ResultBean;
import com.wsj.sys.enums.SysConstants;
import com.wsj.tools.WsjTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jimmy on 2017/6/25.
 */
@RestController
@RequestMapping(value = {"/", "/login"})
public class LoginController {
    @Autowired
    private StaffService staffService;

    /**
     * index跳转
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @SessionCheck(checkedType = SessionCheck.Type.MANAGER)
    @RequestMapping(value = {"/", "/index"})
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(WsjTools.getDomainName(request) + SysConstants.IndexPath.getName());
    }

    /**
     * @param loginName 用户名
     * @param password  密码
     * @param autoLogin 下次自动登录 true自动登录 false不自动登录
     * @return
     */
    @RequestMapping("/login")
    public ResultBean<Staff> login(HttpServletRequest request,String loginName, String password, boolean autoLogin) {
        ResultBean<Staff> result = staffService.userLogin(loginName, password);
        request.getSession().setAttribute(SysConstants.LoginSession.getName(), result.getBean());
        return result;
    }

    @RequestMapping("/logout")
    public ResultBean logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstants.LoginSession.getName());
        return ResultBean.success("退出登录成功!");
    }
}
