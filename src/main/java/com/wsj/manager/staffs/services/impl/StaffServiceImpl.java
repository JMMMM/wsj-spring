package com.wsj.manager.staffs.services.impl;

import com.wsj.manager.staffs.entity.Staff;
import com.wsj.manager.staffs.repository.StaffRepository;
import com.wsj.manager.staffs.services.StaffService;
import com.wsj.sys.bean.ResultBean;
import com.wsj.tools.MD5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 2017/6/23.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private EntityManager em;

    @Override
    public ResultBean userLogin(String loginName, String password) {
        Staff staff = staffRepository.findStaffByPassword(loginName, MD5Helper.encode(password));
        if (null != staff) {
            staffRepository.updateLoginTime(staff.getId());
            return ResultBean.success("登录成功!", staff);
        }
        return ResultBean.failure("登录失败，账号或密码错误!");
    }

    @Override
    public List<Staff> findStaffs(Staff staff, int limit, int pageSize) {
        String sql = "select s.* from staffs s where 1 =1  ";
        List<Object> parameter = new ArrayList<>();
        if (!StringUtils.isEmpty((staff.getName()))) {
            sql += " and name like ?";
            parameter.add("%" + staff.getName() + "%");
        }
        sql += " limit ?,? ";
        parameter.add(limit);
        parameter.add(pageSize);
        Query result = em.createQuery(sql);
        return result.getResultList();
    }
}
