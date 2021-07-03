package com.example.his_test1.dao;

import com.example.his_test1.entity.Doctor;
import com.example.his_test1.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select Password from user where UserName=#{userName}")
    String selectByName(String userName);

    @Select("select u.username, u.realname, u.usetype,u.deptID, d.deptname,u.registLeID, r.registname from user u left join department d on u.deptid=d.id left join registlevel r on u.registLeID = r.id where userName like '%${userName}%'")
    @Results(value={
            @Result(column = "userName", property = "userName"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "useType", property = "useType"),
            @Result(column = "deptID", property = "deptID"),
            @Result(column = "deptname", property = "dept"),
            @Result(column = "registLeID", property = "registLeID"),
            @Result(column = "registname", property = "registName"),
    })
    List<User> showAll(String userName);

    //添加用户
    @Insert("insert into user(UserName, password, realName, useType,deptID, registLeID) values(#{userName}, #{password}, #{realName}, #{useType},#{deptID}, #{registLeID})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addUser(User usr);

    //删除用户
    @Delete("delete from user where userName = #{userName}")
    int deleteUser(String userName);

    //修改用户
    @Update("update user set password = #{password} where userName = #{userName}")
    int updateUser(User user);

    @Select("select * from user where username = #{userName}")
    User getUser(String userName);

    //排班表查找用户
//    @Select("select * from user where deptID = #{deptID} and registLeID = #{registLeID}")
//    List<Doctor> getUserForSch(Doctor user);

    @Select("select * from user u left join scheduleList s on u.userName=s.userName where deptID = #{deptID} and registLeID = #{registLeID}")
    List<Doctor> getUserForSch(Doctor user);

    @Insert("insert into schedulelist(userName, sunMorning, sunAfternoon, monMorning, monAfternoon,tuesMorning, tuesAfternoon,wedsMorning,wedsAfternoon, thursMorning,thursAfternoon, friMorning,friAfternoon, satMorning,satAfternoon) values(#{userName}, #{sunMorning}, #{sunAfternoon}, #{monMorning}, #{monAfternoon},#{tuesMorning}, #{tuesAfternoon},#{wedsMorning},#{wedsAfternoon}, #{thursMorning},#{thursAfternoon}, #{friMorning},#{friAfternoon}, #{satMorning},#{satAfternoon})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addSchedule(User doc);
}
