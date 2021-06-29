package com.example.his_test1.entity;

import lombok.Data;

import java.util.List;

@Data
public class AdminMainMenu {
    private int id;
    private String title;
    private String path;
    private List<AdminSubMenu> subList;
}
