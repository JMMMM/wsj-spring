package com.wsj.services.impl;

import com.wsj.entity.Companys;
import com.wsj.repository.HelloRepository;
import com.wsj.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy on 2017/6/22.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloRepository helloRepository;

    @Override
    public String hello() {
//        return helloRepository.findCompanyName(2145);
        return helloRepository.findOne(111).getName();
    }
}
