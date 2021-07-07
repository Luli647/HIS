package com.example.his_test1.entity;

import lombok.Data;

@Data
public class Disease {
    private int id;
    private String diseaseCode;
    private String diseaseName;
    private String diseaseICD;
    private String diseCategory;
    private int diseCategoryID;

}
