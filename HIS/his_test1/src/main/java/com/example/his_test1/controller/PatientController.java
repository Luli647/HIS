package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.entity.Patient;
import com.example.his_test1.entity.User;
import com.example.his_test1.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @RequestMapping("/userLogin")
    public String login(@RequestBody Patient p){
        return patientService.getUser(p);
    }
}
