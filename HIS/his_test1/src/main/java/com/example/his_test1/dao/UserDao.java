package com.example.his_test1.dao;

import com.example.his_test1.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select Password from user where UserName=#{userName}")
    String selectByName(String userName);

    @Select("select u.username, u.realname, u.usetype, d.deptname, r.registname from user u left join department d on u.deptid=d.id left join registlevel r on u.registLeID = r.id")
    @Results(value={
            @Result(column = "userName", property = "userName"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "useType", property = "useType"),
            @Result(column = "dept", property = "dept"),
            @Result(column = "registname", property = "registName"),
    })
    List<User> showAll();

}
