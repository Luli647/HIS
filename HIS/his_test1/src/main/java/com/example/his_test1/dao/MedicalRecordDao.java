package com.example.his_test1.dao;

import com.example.his_test1.entity.Drug;
import com.example.his_test1.entity.MedicalRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordDao {
    @Select("select * from medicalrecord where caseNumber = #{CaseNumber}")
    @Results({
            @Result(column = "ID", property = "id"),
            @Result(column = "CaseNumber", property = "caseNumber"),
            @Result(column = "RegistID", property = "registID"),
            @Result(column = "Readme", property = "readme"),
            @Result(column = "Present", property = "present"),
            @Result(column = "PresentTreat", property = "presentTreat"),
            @Result(column = "History", property = "history"),
            @Result(column = "Allergy", property = "allergy"),
            @Result(column = "Physique", property = "physique"),
            @Result(column = "Proposal", property = "proposal"),
            @Result(column = "Careful", property = "careful"),
            @Result(column = "CheckResult", property = "checkResult"),
            @Result(column = "Diagnosis", property = "diagnosis"),
            @Result(column = "Handling", property = "handling"),
            @Result(column = "CaseState", property = "caseState"),
    })
    MedicalRecord findMedicalRecordbyCaseNumber(String CaseNumber);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into medicalrecord(caseNumber, registID, readme, present, presentTreat, history, allergy, physique, proposal, careful, caseState) "
            + "values(#{caseNumber},#{registID},#{readme},#{present},#{presentTreat},#{history},#{allergy},#{physique},#{proposal},#{careful},#{caseState})")
    void creatMedicalRecord(MedicalRecord medicalRecord);

    @Update("<script>"
            +"update medicalrecord set"
            +"<if test=\"registID != null\"> registID = #{registID},</if>"
            +"<if test=\"readme != null\"> readme = #{readme},</if>"
            +"<if test=\"present != null\"> present = #{present},</if>"
            +"<if test=\"presentTreat != null\"> presentTreat = #{presentTreat},</if>"
            +"<if test=\"history != null\"> history = #{history},</if>"
            +"<if test=\"allergy != null\"> allergy = #{allergy},</if>"
            +"<if test=\"physique != null\"> physique = #{physique},</if>"
            +"<if test=\"proposal != null\"> proposal = #{proposal},</if>"
            +"<if test=\"careful != null\"> careful = #{careful},</if>"
            +"<if test=\"caseState != null\"> caseState = #{caseState},</if>"
            +" where caseNumber = #{caseNumber}"
            +"</script>")
    void updateMedicalRecord(MedicalRecord medicalRecord);
}
