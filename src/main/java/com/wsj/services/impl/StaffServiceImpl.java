package com.wsj.services.impl;

import com.wsj.bean.ResultBean;
import com.wsj.entity.Staff;
import com.wsj.repository.StaffRepository;
import com.wsj.services.StaffService;
import com.wsj.tools.MD5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Jimmy on 2017/6/23.
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private EntityManager em;

    @Override
    public ResultBean userLogin(String loginName, String password) {
        Staff staff = staffRepository.findStaffByPassword(loginName, MD5Helper.encode(password));
        return ResultBean.success("登录成功!",staff);
    }

    @Override
    public List<Staff> findStaffs(Staff staff, int limit, int pageSize) {
        String sql = "select s from Staff s where 1 =1  ";
        if(!StringUtils.isEmpty((staff.getName()))){
            sql += " and name like '%"+staff.getName()+"%'";
        }
        sql += " limit "+limit+","+pageSize;
        Query result = em.createQuery(sql);
        return result.getResultList();
    }
}
