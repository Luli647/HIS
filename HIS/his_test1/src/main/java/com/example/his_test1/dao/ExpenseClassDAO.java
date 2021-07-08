package com.example.his_test1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.his_test1.entity.Drug;
import com.example.his_test1.entity.ExpenseClass;

@Repository
public interface ExpenseClassDAO {
	
	@Select("select * from ExpenseClass where ExpCode = #{expCode} and DelMark != 0")
	List<ExpenseClass> findByExpCode(String expCode);
	
	@Select("select * from ExpenseClass where ExpName = #{expName} and DelMark != 0")
	ExpenseClass findByExpName(String expName);
	
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "ID")
	@Insert("insert into ExpenseClass(ExpCode, ExpName)"
			+ "values(#{expCode},#{expName})")
	void insertExpenseClass(ExpenseClass expenseClass);
	
	@Update("<script>"
			+"update ExpenseClass set"
			+"<if test=\"expCode != null\"> ExpCode = #{expCode},</if>"
			+"<if test=\"expName != null\"> ExpName = #{expName},</if>"
			+"ID = #{id}"
			+" where ID = #{id} and DelMark != 0"
			+"</script>")
	void updateExpenseClass(ExpenseClass expenseClass);
	
	@Update("update ExpenseClass set DelMark = 0 where ID = #{id}")
	void deleteExpenseClass(ExpenseClass expenseClass);
}
