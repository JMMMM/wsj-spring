package com.wsj.manager.customers.services;


import com.wsj.manager.customers.entity.Customer;
import com.wsj.sys.bean.PageBean;
import com.wsj.sys.bean.ResultBean;

/**
 * Created by jimmy on 2017/6/25.
 */
public interface CustomerService {
    /**
     * 搜索
     *
     * @param customer 用来做筛选
     * @param pageBean 分页
     * @return
     */
    PageBean<Customer> findByPage(Customer customer, PageBean pageBean);

    /**
     * @param customer 有ID时update，没ID时insert
     * @return
     */
    ResultBean<Customer> register(Customer customer);

    Customer findByLoginName(String loginName);


    ResultBean modifyCustomerStatus(int customerId, int status);

    ResultBean login(String loginName, String password);
}
