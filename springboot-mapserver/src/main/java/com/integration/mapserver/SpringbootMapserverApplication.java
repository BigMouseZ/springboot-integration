package com.integration.mapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
/*extends SpringBootServletInitializer
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootMapserverApplication.class);
		}*/
@SpringBootApplication
@ServletComponentScan
public class SpringbootMapserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMapserverApplication.class, args);
	}

}
