package com.example.his_test1.dao;

import com.example.his_test1.entity.CheckApply;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckApplyDao {

    @Select("select c.itemID, c.name, f.deptID, c.state, f.price ,c.result from checkapply c, fmeditem f  where c.itemID = f.id and c.medicalID = ${MedicalID}")
    @Results({
            @Result(column = "itemID", property = "itemID"),
            @Result(column = "name", property = "name"),
            @Result(column = "deptID", property = "deptID"),
            @Result(column = "state", property = "state"),
            @Result(column = "price", property = "price"),
            @Result(column = "result", property = "result"),
    })
    List<CheckApply> findCheckApplybyMedicalID(int MedicalID);
}
