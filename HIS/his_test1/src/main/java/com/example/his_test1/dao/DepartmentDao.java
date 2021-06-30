package com.example.his_test1.dao;

import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.QueryInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao {
    @Select("select * from department where id like '%${id}%'")
    public List<Department> getAllDep(String id);

    @Insert("insert into department(id,name,type) values(#{id}, #{name}, #{type})")
    public int addDep(Department dep);

    @Delete("delete from department where id = #{id}")
    public int deleteDep(String id);

    @Update("update department set name = #{name}, type = #{type} where id = #{id}")
    public int updateDep(Department dep);

    @Select("select * from department where id = #{id}")
    public Department getUpdateDep(String id);
}
