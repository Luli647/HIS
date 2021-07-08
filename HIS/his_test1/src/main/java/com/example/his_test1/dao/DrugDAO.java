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

@Repository
public interface DrugDAO {
	
    @Select("select count(*) from Drugs where DelMark != 0")
    int getDrugNum();
	
	@Select("select * from Drugs where DelMark != 0")
	@Results({
		@Result(property = "code", column="DrugsCode"),
		@Result(property = "name", column="DrugsName"),
		@Result(property = "format", column="DrugsFormat"),
		@Result(property = "unit", column="DrugsUnit"),
		@Result(property = "dosageid", column="DrugsDosageID"),
		@Result(property = "typeid", column="DrugsTypeID"),
		@Result(property = "price", column="DrugsPrice")
		})
	List<Drug> fingAllDrugs();
	
	@Select("select MnemonicCode from Drugs")
	List<String> findAllMnemonicCodes();
	
	@Select("select * from Drugs where DrugsName = #{name} and Manufacturer = #{manufacturer}")
	List<Drug> findByNameAndManufacturer(String name, String manufacturer);
	
	@Select("select * from Drugs where ID = #{id} and DelMark != 0")
	@Results({
		@Result(property = "code", column="DrugsCode"),
		@Result(property = "name", column="DrugsName"),
		@Result(property = "format", column="DrugsFormat"),
		@Result(property = "unit", column="DrugsUnit"),
		@Result(property = "dosageid", column="DrugsDosageID"),
		@Result(property = "typeid", column="DrugsTypeID"),
		@Result(property = "price", column="DrugsPrice")
		})
	Drug findByID(int id);
	
	@Select("<script>"
			+ "select * from Drugs where DelMark != 0 and ID in"
			+ "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
				+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	@Results({
			@Result(property = "code", column="DrugsCode"),
			@Result(property = "name", column="DrugsName"),
			@Result(property = "format", column="DrugsFormat"),
			@Result(property = "unit", column="DrugsUnit"),
			@Result(property = "dosageid", column="DrugsDosageID"),
			@Result(property = "typeid", column="DrugsTypeID"),
			@Result(property = "price", column="DrugsPrice")
			})
	List<Drug> findListByID(List<Integer> ids);
	
	@Select("select * from Drugs where DelMark != 0 and MnemonicCode like concat(#{mnemonicCode}, '%');")
	@Results({
		@Result(property = "code", column="DrugsCode"),
		@Result(property = "name", column="DrugsName"),
		@Result(property = "format", column="DrugsFormat"),
		@Result(property = "unit", column="DrugsUnit"),
		@Result(property = "dosageid", column="DrugsDosageID"),
		@Result(property = "typeid", column="DrugsTypeID"),
		@Result(property = "price", column="DrugsPrice")
		})
	List<Drug> findByMnemonicCode(String mnemonicCode);
	
	@Select("select * from Drugs where DelMark != 0 and DrugsCode = #{drugsCode}")
	@Results({
		@Result(property = "code", column="DrugsCode"),
		@Result(property = "name", column="DrugsName"),
		@Result(property = "format", column="DrugsFormat"),
		@Result(property = "unit", column="DrugsUnit"),
		@Result(property = "dosageid", column="DrugsDosageID"),
		@Result(property = "typeid", column="DrugsTypeID"),
		@Result(property = "price", column="DrugsPrice")
		})
	Drug findByDrugsCode(int drugsCode);
	
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "ID")
	@Insert("insert into Drugs(DrugsCode, DrugsName, DrugsFormat, DrugsUnit, Manufacturer, DrugsDosageID, DrugsTypeID, DrugsPrice, MnemonicCode, CreationDate) "
			+ "values(#{code},#{name},#{format},#{unit},#{manufacturer},#{dosageid},#{typeid},#{price},#{mnemonicCode},#{creationDate})")
	void insertDrug(Drug drug);
	
	@Update("<script>"
			+"update Drugs set"
			+"<if test=\"code != null\"> DrugsCode = #{code},</if>"
			+"<if test=\"name != null\"> DrugsName = #{name},</if>"
			+"<if test=\"format != null\"> DrugsFormat = #{format},</if>"
			+"<if test=\"unit != null\"> DrugsUnit = #{unit},</if>"
			+"<if test=\"manufacturer != null\"> Manufacturer = #{manufacturer},</if>"
			+"<if test=\"dosageid != null\"> DrugsDosageID = #{dosageid},</if>"
			+"<if test=\"typeid != null\"> DrugsTypeID = #{typeid},</if>"
			+"<if test=\"price != null\"> DrugsPrice = #{price},</if>"
			+"<if test=\"mnemonicCode != null\"> MnemonicCode = #{mnemonicCode},</if>"
			+"<if test=\"lastUpdateDate != null\"> LastUpdateDate = #{lastUpdateDate},</if>"
			+"ID = #{id}"
			+" where ID = #{id} and DelMark != 0"
			+"</script>")
	void updateDrug(Drug drug);
	
	@Update("update Drugs set DelMark = 0 where ID = #{id}")
	void deleteDrug(Drug drug);
	
}
