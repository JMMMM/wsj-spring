package com.wsj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy on 2017/6/22.
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @RequestMapping("")
    public String helloWorld() {
        return "Hello world!";
    }
}
