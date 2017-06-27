package com.wsj.manager.customers.services;


import com.wsj.manager.customers.entity.Customer;
import com.wsj.sys.bean.ResultBean;

import java.util.List;

/**
 * Created by jimmy on 2017/6/25.
 */
public interface CustomerService {
    /**
     * 搜索
     * @param customer 用来做筛选
     * @param limit 分页
     * @param pageSize
     * @return
     */
    List<Customer> findByPage(Customer customer, int limit, int pageSize);

    /**
     *
     * @param customer 有ID时update，没ID时insert
     * @return
     */
    ResultBean<Customer> saveOrUpdate(Customer customer);
}
