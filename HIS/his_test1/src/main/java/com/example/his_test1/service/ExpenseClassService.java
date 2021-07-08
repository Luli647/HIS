package com.example.his_test1.service;

import java.util.List;

import com.example.his_test1.entity.ExpenseClass;

public interface ExpenseClassService {

	//通过编码查找费用科目
	List<ExpenseClass> findExpenseClassByExpCode(String expCode);
	
	//通过名称查找费用科目
	ExpenseClass findExpenseClassByExpName(String expName);
	
	//删除
	void delateExpenseClass(ExpenseClass expenseClass);
	
	//修改
	void updateExpenseClass(ExpenseClass expenseClass);
	
	
}
