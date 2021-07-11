package com.example.his_test1.dao;

import com.example.his_test1.entity.DrugsDetailed;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugsDetailedDao {
    @Select("select d.drugsName, d.drugsFormat, d.drugsUnit, x.drugsUsage, x.dosage, x.frequency from drugs d, drugsdetailed x where x.drugsID = d.id and x.drugsTempID = ${DrugsTempID}")
    @Results({
            @Result(column = "drugsName", property = "drugsName"),
            @Result(column = "drugsFormat", property = "drugsFormat"),
            @Result(column = "drugsUnit", property = "drugsUnit"),
            @Result(column = "drugsUsage", property = "drugsUsage"),
            @Result(column = "dosage", property = "dosage"),
            @Result(column = "frequency", property = "frequency")
    })
    List<DrugsDetailed> findDrugsDetailedbyDrugsTempID(int DrugsTempID);
}
