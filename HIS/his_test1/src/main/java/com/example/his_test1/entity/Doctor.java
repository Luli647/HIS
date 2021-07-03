package com.example.his_test1.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Doctor extends User{
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
