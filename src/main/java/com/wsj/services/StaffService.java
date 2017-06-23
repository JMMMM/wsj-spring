package com.wsj.services;

import com.wsj.bean.ResultBean;

/**
 * Created by Jimmy on 2017/6/23.
 */
public interface StaffService {
    //用户登录接口
    ResultBean userLogin(String userName, String password);
}
