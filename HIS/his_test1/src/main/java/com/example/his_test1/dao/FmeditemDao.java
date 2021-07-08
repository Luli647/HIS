package com.example.his_test1.dao;

import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.Fmeditem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FmeditemDao {
    @Select("select * from fmeditem where itemcode like '%${itemcode}%'")
    List<Fmeditem> getAllItem(String itemCode);

    @Delete("delete from fmeditem where itemcode = #{itemcode}")
    int deleteItem(String itemcode);

    @Update("update fmeditem set itemName = #{itemName}, format = #{format}, price = #{price}")
    int updateItem(Fmeditem item);

    @Insert("insert into fmeditem(itemcode, itemname, format, price)values(#{itemCode}, #{itemName}, #{format}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addItem(Fmeditem item);

    @Select("select * from fmeditem where itemCode = #{itemCode}")
    Fmeditem getItem(String itemCode);

}
