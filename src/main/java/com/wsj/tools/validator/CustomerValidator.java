package com.wsj.tools.validator;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.repository.CustomerRepository;
import com.wsj.sys.enums.ErrorCode;
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
    public ErrorCode validate(Customer customer) {
        if(customerRepository.findCustomerByLoginName(customer.getLoginName())!=null) return ErrorCode.LOGINNAME_EXISTS;
        if(customer.getPassword().length()<6) return ErrorCode.PASSWORD_ERROR;
        if(customer.getName().length()<0) return ErrorCode.USERNAME_ERROR;
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(customer.getPhone());
        if(!matcher.matches()) return ErrorCode.PHONE_ERROR;
        return ErrorCode.SUCCESS;
    }
}
