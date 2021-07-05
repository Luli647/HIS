package com.example.his_test1.dao;

import com.example.his_test1.entity.Patient;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao {
    @Select("select * from patient where userName=#{userName}")
    Patient getUser(Patient p);
}
