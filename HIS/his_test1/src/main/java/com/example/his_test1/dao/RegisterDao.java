package com.example.his_test1.dao;

import com.example.his_test1.entity.Register;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterDao {

    @Insert("insert into register(caseNumber,realName, gender, idnumber, age, homeAddress, visitDate,noon, deptID, userID, registLeID, registTime) values(#{caseNumber},#{realName}, #{gender}, #{idnumber}, #{age}, #{homeAddress}, #{visitDate},#{noon}, #{deptID}, #{userID}, #{registLeID}, #{registTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addRegister(Register register);

    @Select("select caseNumber from register order by id desc limit 1")
    String getCaseNumber();

    @Select("select * from register where visitState = 1 and userID = #{UserID}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "caseNumber", property = "caseNumber"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "gentle", property = "gentle"),
            @Result(column = "age", property = "age")
    })
    List<Register> findByUserID(int UserID);

    @Select("select * from register where visitState = 3 and userID = #{UserID}")
    @Results({
            @Result(column = "caseNumber", property = "caseNumber"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "age", property = "age")
    })
    List<Register> findDoneByUserID(int UserID);

    @Select("select * from register where visitState = 1 and deptID = #{DeptID}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "caseNumber", property = "caseNumber"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "gentle", property = "gentle"),
            @Result(column = "age", property = "age")
    })
    List<Register> findByDeptID(int DeptID);

    @Select("select * from register where visitState = 3 and deptID = #{DeptID}")
    @Results({
            @Result(column = "caseNumber", property = "caseNumber"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "age", property = "age")
    })
    List<Register> findDoneByDeptID(int DeptID);
}
