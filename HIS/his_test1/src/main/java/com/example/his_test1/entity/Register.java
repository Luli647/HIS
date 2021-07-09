package com.example.his_test1.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Register {
    private int id;
    private String caseNumber;
    private String realName;
    private int Gender;
    private String idnumber;
    private int age;
    private String homeAddress;
    private java.sql.Date visitDate;
    private String noon;
    private int deptID;
    private int userID;
    private int registLeID;
    private Date registTime;
}
