package com.example.his_test1.service;

import java.util.List;

import com.example.his_test1.entity.Drug;

public interface DrugManagementService {
	//药品管理-查询
	List<Drug> findDrugByMnemonicCode(String mnemonicCode);
		
	//药品管理-新增
	void insertDrug(Drug drug);
		
	//药品管理-修改
	void updateDrug(Drug drug);
		
	//药品管理-删除
	void deleteDrug(Drug drug);
		
	//药品管理-导入
	void importDrugs(List<Drug> drugs);
		
	//获取所有药品
	List<Drug> findAllDrugs();
	
	//获取药品数目
	int getDrugNum();
		
	//获取所有助记码
	List<String> findAllMnemonicCodes();
	
}
