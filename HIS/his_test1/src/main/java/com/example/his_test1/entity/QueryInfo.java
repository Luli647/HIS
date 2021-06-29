package com.example.his_test1.entity;

import lombok.Data;

@Data
public class QueryInfo {
    private String id;
    private int pageNum = 1;
    private int pageSize = 10; //每页最大数
}
