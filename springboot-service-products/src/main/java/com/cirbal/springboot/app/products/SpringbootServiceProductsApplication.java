package com.cirbal.springboot.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableEurekaClient
//@ComponentScan("com.cirbal.springboot.app.products.controllers") // to scan packages mentioned
public class SpringbootServiceProductsApplication {
//	private final static Logger log = LoggerFactory.getLogger(SpringbootServiceProductsApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringbootServiceProductsApplication.class, args);

	}

}
