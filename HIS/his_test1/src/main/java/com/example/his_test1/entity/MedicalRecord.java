package com.example.his_test1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class MedicalRecord {
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "caseNumber")
    private String caseNumber;
    @JSONField(name = "registID")
    private int registID;
    @JSONField(name = "readme")
    private String readme;
    @JSONField(name = "present")
    private String present;
    @JSONField(name = "presentTreat")
    private String presentTreat;
    @JSONField(name = "history")
    private String history;
    @JSONField(name = "allergy")
    private String allergy;
    @JSONField(name = "physique")
    private String physique;
    @JSONField(name = "proposal")
    private String proposal;
    @JSONField(name = "careful")
    private String careful;
    @JSONField(name = "checkResult")
    private String checkResult;
    @JSONField(name = "diagnosis")
    private String diagnosis;
    @JSONField(name = "handling")
    private String handling;
    @JSONField(name = "caseState")
    private int caseState;
}
