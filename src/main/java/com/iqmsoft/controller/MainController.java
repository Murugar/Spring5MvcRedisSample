package com.iqmsoft.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iqmsoft.service.RedisCacheService;


@Controller
public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private RedisCacheService redisCacheService;

    @RequestMapping("/")
    public String view() {
        
        redisCacheService.putSessionObject("123", "zoeSoft");
       
        redisCacheService.getSessionObject("123");
       
        redisCacheService.clearSessionObject("123");
        
        redisCacheService.getSessionObject("123");
        return "index";
    }
}
