package com.example.his_test1.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PatientCheck {
    private int id;
    private int medicalID;
    private String realName;
    private int age;
    private String gender;
    private int deptID;
    private String deptName;
    private int docID;
    private String docName;
    private String position;
    private Date checkCreateTime;
    private String address;
    private int itemID;
    private String item;
}
