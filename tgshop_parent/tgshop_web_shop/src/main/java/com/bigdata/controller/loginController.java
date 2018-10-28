package com.bigdata.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/10/24 20:25
 * @description
 */
@RestController
@RequestMapping("login")
public class loginController {
    @RequestMapping("showName")
    public Map showName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap map = new HashMap();
        map.put("username",name);
        return map;
    }
}
