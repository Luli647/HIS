package com.example.his_test1.dao;

import com.example.his_test1.entity.Register;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDao {

    @Insert("insert into register(caseNumber,realName, gender, idnumber, age, homeAddress, visitDate,noon, deptID, userID, registLeID, registTime) values(#{caseNumber},#{realName}, #{gender}, #{idnumber}, #{age}, #{homeAddress}, #{visitDate},#{noon}, #{deptID}, #{userID}, #{registLeID}, #{registTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addRegister(Register register);

    @Select("select caseNumber from register order by id desc limit 1")
    String getCaseNumber();
}
