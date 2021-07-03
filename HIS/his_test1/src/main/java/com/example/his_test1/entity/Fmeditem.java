package com.example.his_test1.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Fmeditem {
    private int id;
    private String itemCode;
    private String itemName;
    private String format;
    private float price;
    private int expClassID;
    private int deptID;
    private String mnemonicCode;
    private Date creationDate;
    private Date lastUpdate;
    private int recordType;
    private int delMark;
}
