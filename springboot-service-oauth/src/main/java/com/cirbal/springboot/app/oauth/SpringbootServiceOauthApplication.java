package com.cirbal.springboot.app.oauth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SpringbootServiceOauthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceOauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String passwordString = "12345";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		for (int i = 0; i < 4; i++) {
			String passBCrypString = passwordEncoder.encode(passwordString);
			System.out.println(passBCrypString);
		}
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
