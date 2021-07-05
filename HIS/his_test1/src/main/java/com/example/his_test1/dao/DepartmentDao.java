package com.example.his_test1.dao;

import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.QueryInfo;
import com.example.his_test1.entity.RegistDep;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao {
    @Select("SELECT DeptCode, DeptName, constantname FROM department LEFT JOIN constantitem ON department.DeptCategoryID = constantitem.ID where DeptCode like '%${deptCode}%'")
    @Results(value = {
            //property是实体类中定义的
            @Result(column = "deptCode", property = "deptCode"),
            @Result(column = "deptName", property = "deptName"),
            @Result(column = "constantName", property = "constantName"),
    })
    List<Department> getAllDep(String deptCode);


    @Insert("insert into department(DeptCode,DeptName,DeptCategoryID) values(#{deptCode}, #{deptName}, #{deptCategoryID})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addDep(Department dep);

    @Delete("delete from department where DeptCode = #{deptCode}")
    int deleteDep(String deptCode);

    @Update("update department set deptName = #{deptName}, deptCategoryID = #{deptCategoryID} where deptCode = #{deptCode}")
    int updateDep(Department dep);

    @Select("select * from department where DeptCode = #{deptCode}")
    public Department getUpdateDep(String deptCode);

    @Select("select id, deptName from department")
    List<Department> getdepList();

    //为挂号获取院系列表
    @Select("select * from constantitem where id<27")
    @Results(value={
            @Result(column = "constantName", property = "text"),
            @Result(property = "children", column = "id",
            many = @Many(select = "com.example.his_test1.dao.SubDepDao.findAll"))
    })
    List<RegistDep> getAllDeps();
}
