package com.example.his_test1.dao;

import java.util.List;

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
}
