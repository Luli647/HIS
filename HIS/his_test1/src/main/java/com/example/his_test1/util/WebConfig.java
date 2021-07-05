package com.example.his_test1.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//全局配置跨域请求
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        /*
         * 1.与访问路径
         * 2.请求来源
         * 3.方法
         * 4.允许携带
         * 5.响应时间
         */
        registry.addMapping("/**")
                .allowedOrigins("Http://localhost:8080","Http://localhost:8082")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS","DELETE")
                .allowCredentials(true)
                .allowedHeaders("*")
                .exposedHeaders("*")
                .maxAge(3600);
    }
}
