package com.example.his_test1.entity;

import lombok.Data;

import java.util.List;

@Data
public class RegistDep {
    private String text;
    private List<RegistSubDep> children;
}
