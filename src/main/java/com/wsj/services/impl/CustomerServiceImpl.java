package com.wsj.services.impl;

import com.wsj.entity.Customer;
import com.wsj.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jimmy on 2017/6/25.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private EntityManager em ;
    @Override
    public List<Customer> findByPage(Customer customer, int limit, int pageSize) {
        String sql = "select c from Customer c where 1 =1 ";
        if(!StringUtils.isEmpty(customer.getPhone())){
            sql += " and phone like "+"'%"+customer.getPhone()+"%' ";
        }
        Query result = em.createQuery(sql);
        return result.getResultList();
    }
}
