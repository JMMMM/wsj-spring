package com.wsj.controller.manager;

import com.wsj.bean.ResultBean;
import com.wsj.entity.Staff;
import com.wsj.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy on 2017/6/23.
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     *
     * @param userName 用户名
     * @param password 密码
     * @param autoLogin 下次自动登录 true自动登录 false不自动登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResultBean<Staff> login(String userName, String password,boolean autoLogin) {
        ResultBean<Staff> result = staffService.userLogin(userName, password);
        return result;
    }
}
