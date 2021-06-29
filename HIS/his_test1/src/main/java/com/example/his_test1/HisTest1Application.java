package com.example.his_test1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.his_test1.dao")
@SpringBootApplication//(scanBasePackages = {"com.example.his_test1.dao"})
public class HisTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(HisTest1Application.class, args);
    }

}
