package com.wsj.manager.customers.services.impl;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.repository.CustomerRepository;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.manager.staffs.entity.Staff;
import com.wsj.sys.bean.PageBean;
import com.wsj.sys.bean.ResultBean;
import com.wsj.tools.OperatorUtil;
import com.wsj.tools.QueryTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jimmy on 2017/6/25.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private EntityManager em;

    @Override
    public PageBean<Customer> findByPage(Customer customer, PageBean pageBean) {
        String sql = "select c.* from customers c where 1 =1 ";
        List<Object> parameter = new ArrayList<Object>();
        if (customer != null) {
            if (!StringUtils.isEmpty(customer.getPhone())) {
                sql += " and phone like ? ";
                parameter.add(customer.getPhone());
            }
        }
        sql += "limit ?,?";
        return QueryTools.queryPageResult(em, sql, parameter, pageBean, Customer.class);
    }

    @Override
    public ResultBean<Customer> saveOrUpdate(Customer customer) {
        Staff staff = OperatorUtil.getOperatorName(session);
        if (customer.getId() == null) {
            customer.setCreatedAt(new Date());
            customer.setUpdatedAt(new Date());
            customer.setCreatedBy(staff.getId());
            customer.setUpdatedBy(staff.getId());
            customerRepository.save(customer);
        } else {
            Customer db = customerRepository.findOne(customer.getId());
            db.setLastLoginAt(customer.getLastLoginAt());
            db.setLoginName(customer.getLoginName());
            db.setName(customer.getName());
            db.setPhone(customer.getPhone());
            db.setPassword(customer.getPassword());
            db.setSex(customer.getSex());
            db.setUpdatedAt(new Date());
            db.setUpdatedBy(staff.getId());
            db.setStatus(customer.getStatus());
        }
        return ResultBean.success("成功");
    }

    @Override
    public ResultBean modifyCustomerStatus(int customerId, int status) {
        customerRepository.modifyCustomerStatus(customerId, status, OperatorUtil.getOperatorName(session).getId());
        return ResultBean.success("更新成功!");
    }
}
