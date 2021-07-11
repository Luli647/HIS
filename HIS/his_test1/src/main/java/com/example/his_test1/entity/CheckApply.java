package com.example.his_test1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class CheckApply {
    private int id;
    private int medicalID;
    private int registID;
    private int itemID;
    private String name;
    private String objective;
    private String position;
    private  int isUrgent;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private int doctorID;
    private int checkOperID;
    private int resultOperID;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;
    private String result;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date resultTime;
    private int state;
    private int price;
    private int deptID;
}
