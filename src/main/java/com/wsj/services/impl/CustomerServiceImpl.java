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
        String sql = "select c from customers c where 1 =1 ";
        if(customer != null){
            if(!StringUtils.isEmpty(customer.getPhone())){
                sql += " and phone like "+"'%"+customer.getPhone()+"%' ";
            }
        }
        sql += "limit "+limit+","+pageSize;
        Query result = em.createNativeQuery(sql);
        return result.getResultList();
    }
}
