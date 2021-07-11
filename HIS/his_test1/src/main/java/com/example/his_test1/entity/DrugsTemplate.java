package com.example.his_test1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class DrugsTemplate {
    private int id;
    private String name;
    private int userID;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private String scope;
    private int delMark;
}
