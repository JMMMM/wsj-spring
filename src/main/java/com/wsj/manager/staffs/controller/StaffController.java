package com.wsj.manager.staffs.controller;

import com.wsj.manager.staffs.entity.Staff;
import com.wsj.manager.staffs.services.StaffService;
import com.wsj.sys.bean.ResultBean;
import com.wsj.sys.enums.SysConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jimmy on 2017/6/23.
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     * @param loginName 用户名
     * @param password  密码
     * @param autoLogin 下次自动登录 true自动登录 false不自动登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBean<Staff> login(HttpServletRequest request, String loginName, String password, boolean autoLogin) {
        ResultBean<Staff> result = staffService.userLogin(loginName, password);
        if (result.isSuccess())
            request.getSession().setAttribute(SysConstants.ManagerLoginSession.getName(), result.getBean());
        return result;
    }

    @RequestMapping("/logout")
    public ResultBean logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstants.ManagerLoginSession.getName());
        return ResultBean.success("退出登录成功!");
    }

    @RequestMapping("/loginUser")
    public ResultBean loginUser(HttpServletRequest request) {
        Staff staff = (Staff) request.getSession().getAttribute(SysConstants.ManagerLoginSession.getName());
        return (null == staff) ? ResultBean.failure("没有登录") : ResultBean.success("返回数据", staff);
    }
}
