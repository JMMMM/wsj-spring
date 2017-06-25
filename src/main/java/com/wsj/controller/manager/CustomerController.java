package com.wsj.controller.manager;

import com.wsj.entity.Customer;
import com.wsj.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/findCustomers")
    public List<Customer> findCustomers(Customer customer,int limit,int pageSize){
        return customerService.findByPage(customer,limit,pageSize);
    }
}
