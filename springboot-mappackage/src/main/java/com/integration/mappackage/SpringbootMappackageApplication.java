package com.integration.mappackage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.integration.mappackage.entity")
@MapperScan( "com.integration.mappackage.dao")
public class SpringbootMappackageApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootMappackageApplication.class, args);

    }
}
