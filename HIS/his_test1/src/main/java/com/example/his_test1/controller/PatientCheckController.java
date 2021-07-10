package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.example.his_test1.dao.PatientCheckDao;
import com.example.his_test1.entity.PatientCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientCheckController {
    @Autowired
    PatientCheckDao patientCheckDao;
    @GetMapping("/getPatientCheck")
    public List<PatientCheck> getPatientCheck(int medicalID){

        List<PatientCheck> patient = patientCheckDao.getPatient(medicalID);
        int n = patient.size();
        for(int i=0; i<n; i++){
            patient.get(i).setDocName(patientCheckDao.getDocName(patient.get(i).getDocID()));
        }
//        String s = JSON.toJSONString(patient);
//        return s;
        return patient;
    }
    @GetMapping("/addResults")
    public String addResults(String results,int medicalID){
        System.out.println(results);
        int i = patientCheckDao.addResults(results,medicalID);
        String str = i >0? "success":"error";
        return str;
    }
}
