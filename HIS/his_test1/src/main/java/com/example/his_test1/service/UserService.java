package com.example.his_test1.service;

import com.example.his_test1.dao.UserDao;
import com.example.his_test1.entity.Doctor;
import com.example.his_test1.entity.QueryInfo;
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
    //获取用户列表
    public List<User> getAllUser(String userName){
        return userDao.showAll(userName);
    }

    //添加用户
    public int addUser(User usr){
        return userDao.addUser(usr);
    }
    //删除用户
    public int deleteUser(String userName){
        return userDao.deleteUser(userName);
    }
    //更新用户
    public int updateUser(User user){
        return userDao.updateUser(user);
    }
    public User getUser(String userName){
        return userDao.getUser(userName);
    }

    public List<Doctor> getUserForSch(Doctor user){
        return userDao.getUserForSch(user);
    }

    //更新排班规则表
    public int addSchedules(List<User> docs){
        int count=0;
        for(int i=0; i<docs.size(); i++){
            count+=userDao.addSchedule(docs.get(i));
        }
        if(count==docs.size()) return 1;
        else return 0;
    }

}
