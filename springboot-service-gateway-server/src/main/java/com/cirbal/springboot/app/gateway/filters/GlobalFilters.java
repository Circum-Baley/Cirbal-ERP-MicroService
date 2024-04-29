package com.cirbal.springboot.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalFilters implements GlobalFilter, Ordered {

	private static final Logger log = LoggerFactory.getLogger(GlobalFilters.class);

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("------Ejecutando Filtro Pre------");
		exchange.getRequest().mutate().headers(h -> h.add("token", "12345"));
//		exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));

		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			log.info("-------Ejecutando FILTRO POST----------");
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor -> {
				exchange.getResponse().getHeaders().add("token", valor);
			});
			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "AzulMarino").build());
//			exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		}));
	}

}
