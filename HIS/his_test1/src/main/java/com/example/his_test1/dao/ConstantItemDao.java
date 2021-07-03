package com.example.his_test1.dao;

import com.example.his_test1.entity.ConstantItem;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstantItemDao {
    //选择的是科室信息
    @Select("select * from constantitem where id<50")
    List<ConstantItem> getAllConst();

}
