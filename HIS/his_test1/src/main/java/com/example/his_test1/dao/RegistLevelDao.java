package com.example.his_test1.dao;

import com.example.his_test1.entity.RegistLevel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistLevelDao {
    @Select("select id, registname from registlevel")
    List<RegistLevel> getRegistLeList();

}
