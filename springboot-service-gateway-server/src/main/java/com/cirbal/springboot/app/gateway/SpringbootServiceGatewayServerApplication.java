package com.cirbal.springboot.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServiceGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceGatewayServerApplication.class, args);
	}

}
