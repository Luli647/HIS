package com.example.his_test1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.his_test1.dao.ExpenseClassDAO;
import com.example.his_test1.entity.ExpenseClass;
import com.example.his_test1.service.ExpenseClassService;

@Service
public class ExpenseClassServiceImpl implements ExpenseClassService{
	
	@Autowired
	ExpenseClassDAO expenseClassDAO;

	@Override
	public List<ExpenseClass> findExpenseClassByExpCode(String expCode) {
		return expenseClassDAO.findByExpCode(expCode);
	}

	@Override
	public ExpenseClass findExpenseClassByExpName(String expName) {
		return expenseClassDAO.findByExpName(expName);
	}

	@Override
	public void delateExpenseClass(ExpenseClass expenseClass) {
		expenseClassDAO.deleteExpenseClass(expenseClass);
	}

	@Override
	public void updateExpenseClass(ExpenseClass expenseClass) {
		expenseClassDAO.updateExpenseClass(expenseClass);
	}

}
