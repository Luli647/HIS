package com.example.his_test1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.his_test1.dao.DrugDAO;
import com.example.his_test1.dao.PrescriptionDAO;
import com.example.his_test1.dao.PrescriptionDetailedDAO;
import com.example.his_test1.entity.Drug;
import com.example.his_test1.entity.PrescriptionDetailed;
import com.example.his_test1.service.DrugstoreWorkstationService;

@Service
public class DrugstoreWorkstationServiceImpl implements DrugstoreWorkstationService{

	@Autowired
	DrugDAO drugDAO;
	
	@Autowired
	PrescriptionDAO prescriptionDAO;
	
	@Autowired
	PrescriptionDetailedDAO prescriptionDetailedDAO;
	
	@Override
	public List<Drug> findPaidDrugsByMedicalID(int medicalID) {
		//根据病历号找到处方号
		List<Integer> prescriptionIDs = prescriptionDAO.findIDByMedicalID(medicalID);
		//根据处方号找到药品号
		List<Integer> drugIDs = prescriptionDetailedDAO.findPaidDrugsIDByPrescriptionIDList(prescriptionIDs);
		//根据药品号找到药品
		List<Drug> drugs = drugDAO.findListByID(drugIDs);
		
		
		return drugs;
	}

	@Override
	public void distributeDrugs(int medicalID, List<Integer> drugIDs) {
		//根据病历号找到处方号
		List<Integer> prescriptionIDs = prescriptionDAO.findIDByMedicalID(medicalID);
		//修改状态为已发药
		prescriptionDetailedDAO.updateStateByPrescriptionIDList(prescriptionIDs, (byte)4);
		
	}

	@Override
	public List<Drug> findDistributedDrugsByMedicalID(int medicalID) {
		//根据病历号找到处方号
		List<Integer> prescriptionIDs = prescriptionDAO.findIDByMedicalID(medicalID);
		//根据处方号找到药品号
		List<Integer> drugIDs = prescriptionDetailedDAO.findDistributedDrugsIDByPrescriptionIDList(prescriptionIDs);
		//根据药品号找到药品
		List<Drug> drugs = drugDAO.findListByID(drugIDs);
		return drugs;
	}

	@Override
	public void returnDrugs(int medicalID, List<Integer> drugIDs) {
		//根据病历号找到处方号
		List<Integer> prescriptionIDs = prescriptionDAO.findIDByMedicalID(medicalID);
		//修改状态为已退药
		prescriptionDetailedDAO.updateStateByPrescriptionIDList(prescriptionIDs, (byte)5);
		
	}

	
}
