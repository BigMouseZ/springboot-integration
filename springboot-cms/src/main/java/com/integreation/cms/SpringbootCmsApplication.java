package com.integreation.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages="com.integreation.cms")
@MapperScan("com.integreation.cms.cmsdao")
@EntityScan("com.integreation.cms.entity")
@CrossOrigin
public class SpringbootCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCmsApplication.class, args);
    }

}
