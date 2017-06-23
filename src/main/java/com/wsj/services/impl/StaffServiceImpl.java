package com.wsj.services.impl;

import com.wsj.bean.ResultBean;
import com.wsj.entity.Staff;
import com.wsj.repository.StaffRepository;
import com.wsj.services.StaffService;
import com.wsj.tools.MD5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy on 2017/6/23.
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public ResultBean userLogin(String userName, String password) {
        Staff staff = staffRepository.findStaffByPassword(userName, MD5Helper.encode(password));

        return ResultBean.success("登录成功!");
    }
}
