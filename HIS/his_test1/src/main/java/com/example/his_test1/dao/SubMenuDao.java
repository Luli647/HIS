package com.example.his_test1.dao;

import com.example.his_test1.entity.AdminSubMenu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMenuDao {
    @Select("select id, title, path from adminsubmenu where mid  =#{mid}")
    List<AdminSubMenu> findAll(int mid);
}
