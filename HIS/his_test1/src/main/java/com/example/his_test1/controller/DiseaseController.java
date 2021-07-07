package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.dao.DiseaseDao;
import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.Disease;
import com.example.his_test1.entity.QueryInfo;
import com.example.his_test1.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiseaseController {
    @Autowired
    DiseaseDao diseaseDao;

    @GetMapping("/allDisease")
    public PageInfo<Disease> selectByPage(QueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<Disease> depList = diseaseDao.getAllDisease(queryInfo.getQuery());
        PageInfo<Disease> pageInfo = new PageInfo<>(depList);
        return pageInfo;
    }

    @RequestMapping("/deleteDisease")
    public String deleteUser(String diseaseCode){
        int i = diseaseDao.deleteDise(diseaseCode);
        String str = i >0? "success":"error";
        return str;
    }

    @RequestMapping("/getDiseaseType")
    @ResponseBody
    public String searchID(){
        List<Disease> it = diseaseDao.getDiseaseType();
        String it_json = JSON.toJSONString(it);
        return it_json;
    }

    @RequestMapping("/addDisease")
    public String addDep(@RequestBody Disease dise){
        int i = diseaseDao.addDisease(dise);
        return i>0? "success": "error";
    }
    @RequestMapping("/updateDisease")
    public String updateUser(@RequestBody Disease dise){
        int i = diseaseDao.updateDisease(dise);
        String str = i >0? "success":"error";
        return str;
    }
    @RequestMapping("getDisease")
    public String getUser(String disease){
        Disease dise = diseaseDao.getDisease(disease);
        String dise_json = JSON.toJSONString(dise);
        return dise_json;
    }
}
