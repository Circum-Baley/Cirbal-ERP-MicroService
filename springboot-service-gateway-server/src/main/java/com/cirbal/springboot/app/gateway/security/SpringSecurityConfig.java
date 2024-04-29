package com.cirbal.springboot.app.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) {
		http.authorizeExchange(exchanges -> exchanges.pathMatchers("/api/security/oauth/**").permitAll()
				.pathMatchers(HttpMethod.GET, "/api/users/**", "/api/products/createProduct",
						"/api/products/getProductName/{name}", "/api/products/list", "/api/items/list",
						"/api/items/detail/{id}/amount/{amount}", "/api/products/detail/{id}")
				.permitAll().pathMatchers(HttpMethod.GET, "/api/users/users/{id}").hasAnyRole("ADMIN", "USER")
				.pathMatchers("/api/products/**", "/api/items/**", "/api/users/users/**").permitAll()// .hasRole("ADMIN").anyExchange()
				.anyExchange().authenticated().and()
				.addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.csrf(csrf -> csrf.disable()));
		return http.build();
	}
}