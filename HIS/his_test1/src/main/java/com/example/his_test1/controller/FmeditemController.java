package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.dao.FmeditemDao;
import com.example.his_test1.entity.Fmeditem;
import com.example.his_test1.entity.QueryInfo;
import com.example.his_test1.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FmeditemController {
    @Autowired
    FmeditemDao fmeditemDao;

    @GetMapping("/allItems")
    public PageInfo<Fmeditem> selectByPage(QueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<Fmeditem> items = fmeditemDao.getAllItem(queryInfo.getQuery());
        PageInfo<Fmeditem> pageInfo = new PageInfo<>(items);
        return pageInfo;
    }

    @RequestMapping("/updateItem")
    public String updateUser(@RequestBody Fmeditem item){

        int i = fmeditemDao.updateItem(item);
        String str = i >0? "success":"error";
        return str;
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(String itemCode){
        int i = fmeditemDao.deleteItem(itemCode);
        String str = i >0? "success":"error";
        return str;
    }

    @RequestMapping("/addItem")
    public String addDep(@RequestBody Fmeditem item){
        int i = fmeditemDao.addItem(item);
        return i>0? "success": "error";
    }

    @RequestMapping("getItem")
    public String getItem(String itemCode){
        Fmeditem item = fmeditemDao.getItem(itemCode);
        String usr_json = JSON.toJSONString(item);
        return usr_json;
    }
}
