package com.example.his_test1.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String realName;
    private String useType;
    private int docTitleID;
    private int docTitle;
    private String isScheduling;
    private int deptID;
    private String dept;
    private int registLeID;
    private String registName;
    private int delMark;
    private boolean sunMorning;
    private boolean sunAfternoon;
    private boolean monMorning;
    private boolean monAfternoon;
    private boolean tuesMorning;
    private boolean tuesAfternoon;
    private boolean wedsMorning;
    private boolean wedsAfternoon;
    private boolean thursMorning;
    private boolean thursAfternoon;
    private boolean friMorning;
    private boolean friAfternoon;
    private boolean satMorning;
    private boolean satAfternoon;
}
