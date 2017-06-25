package com.wsj.services;

import com.wsj.bean.ResultBean;
import com.wsj.entity.Staff;

import java.util.List;

/**
 * Created by Jimmy on 2017/6/23.
 */
public interface StaffService {
    //用户登录接口
    ResultBean<Staff> userLogin(String loginName, String password);

    /**
     * 查询所有用户接口
     * @param staff 过滤条件
     * @return
     */

    List<Staff> findStaffs(Staff staff,int limit ,int pageSize);
}
