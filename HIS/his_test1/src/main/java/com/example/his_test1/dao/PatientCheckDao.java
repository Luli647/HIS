package com.example.his_test1.dao;

import com.example.his_test1.entity.PatientCheck;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientCheckDao {
    @Select("select c.medicalID, r.realName, r.age, d.deptName, c.doctorID, c.position,  c.creationTime, r.homeaddress, f.itemName  from checkapply c left join register r on c.registID = r.id left join department d on d.id = r.deptID  left join fmeditem f on c.itemID= f.id  where c.medicalID = #{medicalID}")
    @Results(value = {
            @Result(column = "medicalID", property = "medicalID"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "age", property = "age"),
            @Result(column = "deptName", property = "deptName"),
            @Result(column = "doctorID", property = "docID"),
            @Result(column = "position", property = "position"),
            @Result(column = "creationtime", property = "checkCreateTime"),
            @Result(column = "homeaddress", property = "address"),
            @Result(column = "itemName", property = "item")
    }
    )
    List<PatientCheck> getPatient(int medicalID);

    @Select("select realname from user where id = #{id}")
    String getDocName(int id);

    @Update("update medicalrecord set checkresult = #{results} where id = #{medicalID}")
    int addResults(String results, int medicalID);

    
}
