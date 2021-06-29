package com.example.his_test1.dao;

import com.example.his_test1.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select userPassword from user where userName=#{userName}")
    public String selectByName(String userName);

    @Select("select * from user")
    List<User> showAll();

}
