package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.dao.DepartmentDao;
import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.QueryInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/allDep")
    public String getDepList(){
        int num = departmentDao.getDepNum();
        //int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<Department> deps =  departmentDao.getAllDep();
        HashMap<String, Object> res = new HashMap<>();
        res.put("depNum", num);
        res.put("data", deps);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }
    @GetMapping("/allDep/{pageNum}/{pageSize}")
    public PageInfo<Department> selectByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Department> depList = departmentDao.getAllDep();
        PageInfo<Department> pageInfo = new PageInfo<>(depList);
        return pageInfo;
    }
}
