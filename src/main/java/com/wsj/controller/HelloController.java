package com.wsj.controller;

import com.wsj.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy on 2017/6/22.
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("")
    public String helloWorld() {
        return helloService.hello();
    }
}
