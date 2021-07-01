package com.example.his_test1.service;

import com.example.his_test1.dao.UserDao;
import com.example.his_test1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public String loginService(User user){
        if(userDao.selectByName(user.getUserName()).equals(user.getPassword())){
            return "correct";
        }
        else{
            return "error";
        }
    }
    public List<User> getAllUser(){
        return userDao.showAll();
    }
}
