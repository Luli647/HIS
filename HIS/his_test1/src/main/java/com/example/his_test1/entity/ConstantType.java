package com.example.his_test1.entity;

import lombok.Data;

import java.util.List;

@Data
public class ConstantType {
    private int id;
    private String constantTypeCode;
    private String constantTypeName;
    private int delMark;
    private List<ConstantItem> constantItemList;
}
