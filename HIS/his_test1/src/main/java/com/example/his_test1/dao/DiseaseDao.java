package com.example.his_test1.dao;

import com.example.his_test1.entity.Disease;
import com.example.his_test1.entity.QueryInfo;
import com.example.his_test1.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseDao {
    @Select("select d.diseaseCode, d.diseaseName, d.diseaseICD, d.diseCategoryID, dt.dicaname from disease d left join disecategory dt on d.diseCategoryID = dt.id where d.diseaseCode like '%${diseaseCode}%'")
    @Results(value = {
            @Result(column = "diseaseCode", property = "diseaseCode"),
            @Result(column = "diseaseName", property = "diseaseName"),
            @Result(column = "diseaseICD", property = "diseaseICD"),
            @Result(column = "diseCategoryID", property = "diseCategoryID"),
            @Result(column = "dicaname", property = "diseCategory")
    })
    List<Disease> getAllDisease(String diseaseCode );

    @Delete("delete from disease where diseasecode = #{diseaseCode}")
    int deleteDise(String diseaseCode);

    @Select("select id, dicaname from disecategory")
    @Results(value = {
            @Result(column = "id", property = "diseCategoryID"),
            @Result(column = "dicaname", property = "diseCategory"),
    })
    List<Disease> getDiseaseType();

    @Insert("insert into Disease(diseaseCode, diseaseName, diseaseICD, diseCategoryID) values(#{diseaseCode}, #{diseaseName}, #{diseaseICD}, #{diseCategoryID})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addDisease(Disease dise);

    @Update("update disease set diseaseName = #{diseaseName}, diseaseICD = #{diseaseICD}, diseCategoryID = #{diseCategoryID} where diseaseCode = #{diseaseCode}")
    int updateDisease(Disease dise);

    @Select("select * from disease where diseaseCode = #{disease}")
    Disease getDisease(String dise);
}
