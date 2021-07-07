package com.example.his_test1.controller;

import com.example.his_test1.dao.FmeditemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FmeditemController {
    @Autowired
    FmeditemDao fmeditemDao;

}
