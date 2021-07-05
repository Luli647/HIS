package com.example.his_test1.dao;

import com.example.his_test1.entity.RegistSubDep;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDepDao {
    @Select("select id, deptname from department where deptcategoryID = #{deptcategory} ")
    @Results(value={
            @Result(column = "id", property = "id"),
            @Result(column = "deptname", property = "text"),
    })
    List<RegistSubDep> findAll(int depecategory);
}
