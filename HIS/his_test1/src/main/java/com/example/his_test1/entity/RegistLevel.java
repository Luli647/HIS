package com.example.his_test1.entity;

import lombok.Data;

@Data
public class RegistLevel {
    private int id;
    private String registCode;
    private String registName;
    private int sequenceNo;
    private float registFee;
    private int registQuota;
    private int delMark;
}
