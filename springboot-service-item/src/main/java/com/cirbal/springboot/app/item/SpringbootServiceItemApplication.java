package com.cirbal.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.cirbal.springboot.app.item")
//@ComponentScan("com.cirbal.springboot.app.item.clientes")
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class SpringbootServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceItemApplication.class, args);
	}
}
