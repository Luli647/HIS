package com.example.his_test1.dao;

import com.example.his_test1.entity.AdminMainMenu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    @Select("select * from adminmainmenu")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "path", property = "path"),
            @Result(property = "subList", column = "id",
                    many = @Many(select = "com.example.his_test1.dao.SubMenuDao.findAll" )
            )
    })
    public List<AdminMainMenu> getMainMenus();

    @Select("select id from adminmainmenu")
    public List<Integer> getId();
}
