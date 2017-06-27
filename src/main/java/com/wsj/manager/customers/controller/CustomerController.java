package com.wsj.manager.customers.controller;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.sys.annotation.SessionCheck;
import com.wsj.sys.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jimmy on 2017/6/25.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 用户列表
     *
     * @param customer
     * @param pageBean
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.MANAGER)
    @RequestMapping("/findCustomers")
    public PageBean<Customer> findCustomers(@RequestParam(name = "customer", required = false) Customer customer,
                                            PageBean pageBean) {
        return customerService.findByPage(customer, pageBean);
    }
}
