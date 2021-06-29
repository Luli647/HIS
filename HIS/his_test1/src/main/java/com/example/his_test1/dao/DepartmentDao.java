package com.example.his_test1.dao;

import com.example.his_test1.entity.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao {
    @Select("select * from department")
    public List<Department> getAllDep();
    @Select("select count(*) from department")
    public int getDepNum();
}
