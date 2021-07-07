package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.his_test1.dao.ConstantItemDao;
import com.example.his_test1.dao.DepartmentDao;
import com.example.his_test1.dao.UserDao;
import com.example.his_test1.entity.*;
import com.example.his_test1.service.RegistLevelService;
import com.example.his_test1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUser")
    public PageInfo<User> selectByPage(QueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<User> userList = userService.getAllUser(queryInfo.getQuery());
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/getDepList")
    @ResponseBody
    public String searchID(){
        List<Department> it = departmentDao.getdepList();
        String it_json = JSON.toJSONString(it);
        return it_json;
    }
    @Autowired
    RegistLevelService registLevelService;
    @RequestMapping("/getRegistLe")
    @ResponseBody
    public List<RegistLevel> getRegistLevelList(){
        return registLevelService.getRegistLeList();
    }

    @RequestMapping("/addUser")
    public String addDep(@RequestBody User usr){
        int i = userService.addUser(usr);
        return i>0? "success": "error";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String userName){
        int i = userService.deleteUser(userName);
        String str = i >0? "success":"error";
        return str;
    }
    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        System.out.println(user);
        int i = userService.updateUser(user);
        String str = i >0? "success":"error";
        return str;
    }
    @RequestMapping("getUser")
    public String getUser(String userName){
        User usr= userService.getUser(userName);
        String usr_json = JSON.toJSONString(usr);
        return usr_json;
    }
    @GetMapping("/getUserForSch")
    public String getUserForSch(User user){
        List<User> users = userService.getUserForSch(user);
        String users_json = JSON.toJSONString(users);
        return users_json;
    }

    @GetMapping("/updateSchedule")
    public String updateSchedule(String s){
        System.out.println(s);
        List<User> doctors = JSONObject.parseArray(s,User.class);
        System.out.println(doctors);
        int i = userService.addSchedules(doctors);
        String str = i >0? "success":"error";
        return str;
    }
    @Autowired
    UserDao userdao;
    @GetMapping("/userRegist1")
    public String userRegist1(int deptID, int k){
        List<DocAvailable> users = userdao.getUserName21(deptID);
        String doc_json = JSON.toJSONString(users);
        return doc_json;
    }
    @GetMapping("/userRegist2")
    public String userRegist2(int deptID, int k){
        List<DocAvailable> users = userdao.getUserName22(deptID);
        String doc_json = JSON.toJSONString(users);
        return doc_json;
    }

}
