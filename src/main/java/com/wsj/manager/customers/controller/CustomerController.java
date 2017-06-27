package com.wsj.manager.customers.controller;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.sys.annotation.SessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * @param customer
     * @param limit
     * @param pageSize
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.MANAGER)
    @RequestMapping("/findCustomers")
    public List<Customer> findCustomers(@RequestParam(name = "customer",required = false) Customer customer,
                                        @RequestParam(name="limit",defaultValue = "0") int limit,
                                        @RequestParam(name="pageSize",defaultValue = "10") int pageSize){
        return customerService.findByPage(customer,limit,pageSize);
    }
}
