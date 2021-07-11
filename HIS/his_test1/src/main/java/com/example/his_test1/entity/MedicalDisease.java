package com.example.his_test1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalDisease {
    private int id;
    private int medicalID;
    private int registID;
    private int diseaselID;
    private int diagnoseType;
    @JSONField(name = "getSiskDate", format="yyyy-MM-dd HH:mm:ss")
    private Date getSiskDate;
    private int diagnoseCate;
    private String diseaseICD;
    private String diseaseName;
}
