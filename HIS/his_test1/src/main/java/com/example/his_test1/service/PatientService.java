package com.example.his_test1.service;

import com.example.his_test1.dao.PatientDao;
import com.example.his_test1.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientDao patientDao;
    public String getUser(Patient p){
        Patient user = patientDao.getUser(p);
        if(p.getPassword().equals(user.getPassword())){
            return "success";
        }
        else return "error";
    }
}
