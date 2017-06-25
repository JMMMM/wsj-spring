package com.wsj.services.impl;

import com.wsj.bean.ResultBean;
import com.wsj.entity.Customer;
import com.wsj.entity.Staff;
import com.wsj.enums.SysConstants;
import com.wsj.repository.CustomerRepository;
import com.wsj.services.CustomerService;
import com.wsj.tools.OperatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by jimmy on 2017/6/25.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager em ;
    @Autowired
    private HttpSession session;
    @Override
    public List<Customer> findByPage(Customer customer, int limit, int pageSize) {
        String sql = "select c.* from customers c where 1 =1 ";
        if(customer != null){
            if(!StringUtils.isEmpty(customer.getPhone())){
                sql += " and phone like "+"'%"+customer.getPhone()+"%' ";
            }
        }
        sql += "limit "+limit+","+pageSize;
        Query result = em.createNativeQuery(sql);
        return result.getResultList();
    }

    @Override
    public ResultBean<Customer> saveOrUpdate(Customer customer) {
        Staff staff = OperatorUtil.getOperatorName(session);
        if(customer.getId() == null){
            customer.setCreatedAt(new Date());
            customer.setUpdatedAt(new Date());
            customer.setCreatedBy(staff.getId());
            customer.setUpdatedBy(staff.getId());
            customerRepository.save(customer);
        }
        else {
            Customer db = customerRepository.findOne(customer.getId());
            db.setLastLoginAt(customer.getLastLoginAt());
            db.setLoginName(customer.getLoginName());
            db.setName(customer.getName());
            db.setPhone(customer.getPhone());
            db.setPassword(customer.getPassword());
            db.setSex(customer.getSex());
            db.setUpdatedAt(new Date());
            db.setUpdatedBy(staff.getId());
        }
        return ResultBean.success("成功");
    }
}
