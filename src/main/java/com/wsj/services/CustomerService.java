package com.wsj.services;

import com.wsj.entity.Customer;

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
    List<Customer> findByPage(Customer customer,int limit,int pageSize);
}
