package com.example.his_test1.service;

import java.util.List;

import com.example.his_test1.entity.Drug;

public interface DrugstoreWorkstationService {
	//门诊发药-查询
	List<Drug> findPaidDrugsByMedicalID(int medicalID);
	
	//门诊发药-发药
	void distributeDrugs(int medicalID, List<Integer> drugIDs);
	
	//门诊退药-查询
	List<Drug> findDistributedDrugsByMedicalID(int medicalID);
	
	//门诊退药-退药
	void returnDrugs(int medicalID, List<Integer> drugIDs);
	
	
}
