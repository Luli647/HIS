package com.example.his_test1.service;

import com.example.his_test1.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service


public class DeptService {
    @Autowired
    DepartmentDao departmentDao;

}
