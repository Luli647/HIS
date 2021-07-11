package com.example.his_test1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class CheckTemplate {
    private int id;
    private String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private char scope;
    private int recordType;
    private int delMark;
}
