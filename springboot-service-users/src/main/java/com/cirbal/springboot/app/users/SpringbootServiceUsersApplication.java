package com.cirbal.springboot.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({ "com.cirbal.springboot.app.commons.users.models.entity" })
//@ComponentScan("com.cirbal.springboot.app")
public class SpringbootServiceUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceUsersApplication.class, args);
	}
}
