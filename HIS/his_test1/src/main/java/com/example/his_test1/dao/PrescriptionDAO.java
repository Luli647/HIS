package com.example.his_test1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.his_test1.entity.Prescription;

@Repository
public interface PrescriptionDAO {

	@Select("select * from Prescription where MedicalID = #{medicalID}")
	List<Prescription> findByMedicalID(int medicalID);
	
	@Select("select ID from Prescription where MedicalID = #{medicalID}")
	List<Integer> findIDByMedicalID(int medicalID);
	
	
}
