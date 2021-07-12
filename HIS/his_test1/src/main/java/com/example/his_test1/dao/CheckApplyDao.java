package com.example.his_test1.dao;

import com.example.his_test1.entity.CheckApply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckApplyDao {

    @Select("select c.id, c.itemID, c.name, f.deptID, c.state, f.price ,c.result from checkapply c, fmeditem f  where c.itemID = f.id and c.medicalID = ${MedicalID}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "itemID", property = "itemID"),
            @Result(column = "name", property = "name"),
            @Result(column = "deptID", property = "deptID"),
            @Result(column = "state", property = "state"),
            @Result(column = "price", property = "price"),
            @Result(column = "result", property = "result"),
    })
    List<CheckApply> findCheckApplybyMedicalID(int MedicalID);

    @Delete("delete from checkapply where ID = #{ID}")
    void deleteCheckApplybyID(int ID);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "ID")
    @Insert("insert into checkapply(MedicalID,RegistID,ItemID,Name,Objective,Position,IsUrgent,Num,CreationTime, DoctorID,CheckOperID,ResultOperID,CheckTime,Result,ResultTime,State,RecordType) "
            + "values(#{medicalID},#{registID},#{itemID},#{name},#{objective},#{position},#{num},#{isUrgent},#{creationTime},#{doctorID},#{checkOperID},#{resultOperID},#{checkTime},#{result},#{resultTime},#{state},#{recordType})")
    void addCheckApply(CheckApply checkApply);
}
