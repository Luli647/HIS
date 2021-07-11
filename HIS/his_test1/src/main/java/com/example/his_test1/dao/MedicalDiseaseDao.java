package com.example.his_test1.dao;

import com.example.his_test1.entity.MedicalDisease;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalDiseaseDao {

    @Select("select d.diseaseICD, d.diseaseName, m.getSiskDate from medicalDisease m, disease d  where m.diseaseID = d.id and m.medicalID = ${MedicalID}")
    @Results({
            @Result(column = "diseaseICD", property = "diseaseICD"),
            @Result(column = "diseaseName", property = "diseaseName"),
            @Result(column = "getSiskDate", property = "getSiskDate")
    })
    List<MedicalDisease> findmedicalDiseasebyMedicalID(int MedicalID);

}
