package com.example.his_test1.dao;

import com.example.his_test1.entity.CheckTemplate;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckTemplateDao {
    @Select("select name from checktemplate where userid = ${UserID}")
    @Results({
            @Result(column = "name", property = "name")
    })
    List<CheckTemplate> findCheckTemplateByUserID(int UserID);
}
