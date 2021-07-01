package com.example.his_test1.controller;

import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.QueryInfo;
import com.example.his_test1.entity.User;
import com.example.his_test1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUser")
    public PageInfo<User> selectByPage(QueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<User> userList = userService.getAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
