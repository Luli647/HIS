package com.example.his_test1.service;

import com.example.his_test1.dao.RegistLevelDao;
import com.example.his_test1.entity.RegistLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistLevelService {
    @Autowired
    RegistLevelDao registLevelDao;

    public List<RegistLevel> getRegistLeList(){
        return registLevelDao.getRegistLeList();
    }
}
