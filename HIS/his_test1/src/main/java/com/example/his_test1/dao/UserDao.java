package com.example.his_test1.dao;

import com.example.his_test1.entity.DocAvailable;
import com.example.his_test1.entity.Doctor;
import com.example.his_test1.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select Password from user where UserName=#{userName}")
    String selectByName(String userName);

    @Select("select useType from user where userName  = #{userName}")
    int selectUserType(User user);

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
    List<User> getUserForSch(User user);

    @Update("update schedulelist set sunMorning =#{sunMorning}, sunAfternoon = #{sunAfternoon}, monMorning = #{monMorning}, monAfternoon = #{monAfternoon},tuesMorning = #{tuesMorning}, tuesAfternoon = #{tuesAfternoon},wedsMorning=#{wedsMorning},wedsAfternoon = #{wedsAfternoon}, thursMorning=#{thursMorning},thursAfternoon=#{thursAfternoon}, friMorning=#{friMorning},friAfternoon=#{friAfternoon}, satMorning=#{satMorning},satAfternoon=#{satAfternoon} where userName = #{userName}")
    int addSchedule(User doc);

    //挂号功能
    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.tuesMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName21(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.tuesAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName22(int deptID);
    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.wedsMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName31(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.wedsAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName32(int deptID);
    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.thursMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName41(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.thursAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName42(int deptID);
    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.friMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName51(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.friAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName52(int deptID);
    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.satMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName61(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.satAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName62(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.monMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName11(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.monAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName12(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.sunMorning=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName1(int deptID);

    @Select("select realName, u.userName, u.id, u.registLeID from schedulelist s left join user u on u.userName = s.userName where deptID = #{deptID} and s.sunAfternoon=true ")
    @Results(value = {
            @Result(column = "realName", property = "title"),
            @Result(column = "userName", property = "value"),
            @Result(column = "id", property = "userID"),
            @Result(column = "registLeID", property = "registLeID"),
    })
    List<DocAvailable> getUserName2(int deptID);

    @Select("select * from schedulelist where userName = #{userName}")
    User getDoc(String userName);

    @Select("select registLeID from user where id = #{userID}")
    int getRegistLeID(int userID);

}
