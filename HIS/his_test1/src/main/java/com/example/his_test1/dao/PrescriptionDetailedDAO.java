package com.example.his_test1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.his_test1.entity.PrescriptionDetailed;

@Repository
public interface PrescriptionDetailedDAO {

	@Select("select * from PrescriptionDetailed where PrescriptionID = #{prescriptionID}")
	List<PrescriptionDetailed> findListByPrescriptionID(int prescriptionID);

//	@Select("select * from PrescriptionDetailed where PrescriptionID in #{prescriptionIDs}")
//	List<PrescriptionDetailed> findListByPrescriptionIDList(List<Integer> prescriptionIDs);

	@Select("<script>"
			+ "select ID from PrescriptionDetailed where PrescriptionID in"
			+ "<foreach item='item' index='index' collection='prescriptionIDs' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	List<Integer> findIDByPrescriptionIDList(List<Integer> prescriptionIDs);

	@Select("<script>"
			+ "select DrugsID from PrescriptionDetailed where PrescriptionID in"
			+ "<foreach item='item' index='index' collection='prescriptionIDs' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	List<Integer> findDrugsIDByPrescriptionIDList(List<Integer> prescriptionIDs);

	@Select("<script>"
			+ "select DrugsID from PrescriptionDetailed where state = 3 and PrescriptionID in"
			+ "<foreach item='item' index='index' collection='prescriptionIDs' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	List<Integer> findPaidDrugsIDByPrescriptionIDList(List<Integer> prescriptionIDs);

	@Select("<script>"
			+ "select DrugsID from PrescriptionDetailed where state = 4 and PrescriptionID in"
			+ "<foreach item='item' index='index' collection='prescriptionIDs' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	List<Integer> findDistributedDrugsIDByPrescriptionIDList(List<Integer> prescriptionIDs);

	@Select("<script>"
			+ "select DrugsID from PrescriptionDetailed where state = #{state} and PrescriptionID in"
			+ "<foreach item='item' index='index' collection='prescriptionIDs' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	List<Integer> findDrugsIDByPrescriptionIDListAndState(List<Integer> prescriptionIDs, int state);

	@Update("<script>"
			+ "update PrescriptionDetailed set state = #{state} where ID in"
			+ "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	void updateStateByIDList(List<Integer> ids);

	@Update("<script>"
			+ "update PrescriptionDetailed set state = #{state} where PrescriptionID in"
			+ "<foreach item='item' index='index' collection='prescriptionIDs' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	void updateStateByPrescriptionIDList(List<Integer> prescriptionIDs, byte State);

	@Select("select d.drugsName, d.drugsFormat, d.drugsPrice, p.drugsUsage, p.dosage, p.frequency, p.amount from drugs d, prescriptionDetailed p where d.id = p.drugsID and p.prescriptionID = ${PrescriptionID}")
	@Results({
			@Result(column = "drugsName", property = "drugsName"),
			@Result(column = "drugsFormat", property = "drugsFormat"),
			@Result(column = "drugsPrice", property = "drugsPrice"),
			@Result(column = "drugsUsage", property = "drugsUsage"),
			@Result(column = "dosage", property = "dosage"),
			@Result(column = "frequency", property = "frequency"),
			@Result(column = "amount", property = "amount"),
	})
	List<PrescriptionDetailed> findPrescriptionDetailsbyPrescriptionID(int PrescriptionID);
}
