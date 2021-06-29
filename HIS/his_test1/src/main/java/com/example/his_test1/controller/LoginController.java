package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.dao.UserDao;
import com.example.his_test1.entity.User;

import com.example.his_test1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class LoginController {

   @Autowired
    private UserService userService;
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flag = userService.loginService(user);
        HashMap<String, Object> res = new HashMap<>();
        //用户存在
        res.put("flag", flag);
        res.put("user", user);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }


}
