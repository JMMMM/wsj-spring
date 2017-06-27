package com.wsj.manager.staffs.services;


import com.wsj.manager.staffs.entity.Staff;
import com.wsj.sys.bean.ResultBean;

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

    List<Staff> findStaffs(Staff staff, int limit, int pageSize);
}
