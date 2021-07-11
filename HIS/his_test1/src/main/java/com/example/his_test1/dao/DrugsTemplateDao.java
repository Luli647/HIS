package com.example.his_test1.dao;

import com.example.his_test1.entity.DrugsTemplate;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugsTemplateDao {
    @Select("select id, name, scope from drugsTemplate where userID = ${UserID}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "scope", property = "scope")
    })
    List<DrugsTemplate> findDrugsTemplateByUserID(int UserID);
}
