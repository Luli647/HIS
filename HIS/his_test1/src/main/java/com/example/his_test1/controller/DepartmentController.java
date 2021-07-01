package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.dao.ConstantItemDao;
import com.example.his_test1.dao.DepartmentDao;
import com.example.his_test1.entity.ConstantItem;
import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.QueryInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.HashMap;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    ConstantItemDao constantItemDao;
//    @RequestMapping("/allDep")
//    public String getDepList(){
//        int num = departmentDao.getDepNum();
//        //int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
//        List<Department> deps =  departmentDao.getAllDep();
//        HashMap<String, Object> res = new HashMap<>();
//        res.put("depNum", num);
//        res.put("data", deps);
//        String res_string = JSON.toJSONString(res);
//        return res_string;
//    }
    @GetMapping("/allDep")
    public PageInfo<Department> selectByPage(QueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<Department> depList = departmentDao.getAllDep(queryInfo.getQuery());
        PageInfo<Department> pageInfo = new PageInfo<>(depList);
        return pageInfo;
    }

    @RequestMapping("/addDep")
    public String addDep(@RequestBody Department dep){
        int i = departmentDao.addDep(dep);
        return i>0? "success": "error";
    }
    @RequestMapping("/addDepsearchID")
    @ResponseBody
    public String searchID(){
        List<ConstantItem> it = constantItemDao.getAllConst();
        String it_json = JSON.toJSONString(it);
        return it_json;
    }

    @RequestMapping("/deleteDep")
    public String deleteUser(String id){
        int i = departmentDao.deleteDep(id);
        String str = i >0? "success":"error";
        return str;
    }

    @RequestMapping("/updateDep")
    public String updateDep(@RequestBody Department dep){
        int i=departmentDao.updateDep(dep);
        String str = i>0? "success": "error";
        return str;
    }

    @RequestMapping("/getUpdateDep")
    public String getUpdateDep(String id){
        Department dep = departmentDao.getUpdateDep(id);
        String dep_json = JSON.toJSONString(dep);
        return dep_json;
    }
}
