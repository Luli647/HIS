package com.example.his_test1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.his_test1.dao.DrugDAO;
import com.example.his_test1.entity.Drug;
import com.example.his_test1.service.DrugManagementService;

@Service
public class DrugManagementServiceImpl implements DrugManagementService{

	@Autowired
	DrugDAO drugDAO;
	
	@Override
	public List<Drug> findDrugByMnemonicCode(String mnemonicCode) {
		return drugDAO.findByMnemonicCode(mnemonicCode);
	}

	@Override
	public void insertDrug(Drug drug) {
		drugDAO.insertDrug(drug);
	}

	@Override
	public void updateDrug(Drug drug) {
		drugDAO.updateDrug(drug);
	}

	@Override
	public void deleteDrug(Drug drug) {
		drugDAO.deleteDrug(drug);
	}

	@Override
	public void importDrugs(List<Drug> drugs) {
		for(Drug drug:drugs) {
			if(drugDAO.findByNameAndManufacturer(drug.getName(), drug.getManufacturer()).size() == 0)
				drugDAO.insertDrug(drug);
		}
	}

	@Override
	public List<Drug> findAllDrugs() {
		return 	drugDAO.fingAllDrugs();
	}

	@Override
	public int getDrugNum() {
		return drugDAO.getDrugNum();
	}

	@Override
	public List<String> findAllMnemonicCodes() {
		return drugDAO.findAllMnemonicCodes();
	}
	
}
