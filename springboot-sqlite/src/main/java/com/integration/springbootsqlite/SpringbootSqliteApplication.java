package com.integration.springbootsqlite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = "com.integration")
public class SpringbootSqliteApplication {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSqliteApplication.class, args);




    }

}
