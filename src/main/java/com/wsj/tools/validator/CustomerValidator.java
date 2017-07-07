package com.wsj.tools.validator;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jimmy on 2017/7/7.
 */
public class CustomerValidator implements ValidatorFunction<Customer>{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public boolean validate(Customer customer) {
        if(customerRepository.findCustomerByLoginName(customer.getLoginName())!=null) return false;
        if(customer.getPassword().length()<6) return false;
        if(customer.getName().length()<0) return false;
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(customer.getPhone());
        if(!matcher.matches()) return false;
        return true;
    }
}
