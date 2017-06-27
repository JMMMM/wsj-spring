package com.wsj.manager.staffs.controller;

import com.wsj.manager.staffs.entity.Staff;
import com.wsj.manager.staffs.services.StaffService;
import com.wsj.sys.annotation.SessionCheck;
import com.wsj.sys.bean.ResultBean;
import com.wsj.sys.enums.SysConstants;
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
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.MANAGER)
    @RequestMapping(value = {"/", "/index"})
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + SysConstants.IndexPath.getName());
    }

    /**
     * @param loginName 用户名
     * @param password  密码
     * @param autoLogin 下次自动登录 true自动登录 false不自动登录
     * @return
     */
    @RequestMapping("/login")
    public ResultBean<Staff> login(HttpServletRequest request, HttpServletResponse response, String loginName, String password, boolean autoLogin) {
        ResultBean<Staff> result = staffService.userLogin(loginName, password);
        request.getSession().setAttribute(SysConstants.LoginSession.getName(), result.getBean());
        if(result.isSuccess()){
            try {
                response.sendRedirect(request.getContextPath() + SysConstants.IndexPath.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}