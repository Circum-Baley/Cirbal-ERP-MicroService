package com.cirbal.springboot.app.gateway.filters.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class EjemploGatewayFilterFactory
		extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {

	private static final Logger log = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

	public EjemploGatewayFilterFactory() {
		super(Configuracion.class);
	}

	@Override
	public GatewayFilter apply(Configuracion config) {
		return (exchance,chain)->{
//		return new OrderedGatewayFilter((exchance, chain) -> {
			log.info("Execution PRE GATEWAY FILTER FACTORY{}", config.cookieMessage);

			return chain.filter(exchance).then(Mono.fromRunnable(() -> {
				Optional.ofNullable(config.cookieValue).ifPresent(cookie -> {
					exchance.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build());
				});
				log.info("Execution POST GATEWAY FILTER FACTORY{}", config.cookieMessage);
			}));
//		}, 2);
		};
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "EjemploCookie";
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("cookieMessage", "cookieName", "cookieValue");
	}

	public static class Configuracion {
		private String cookieMessage;
		private String cookieValue;
		private String cookieName;

		public String getCookieMessage() {
			return cookieMessage;
		}

		public void setCookieMessage(String cookieMessage) {
			this.cookieMessage = cookieMessage;
		}

		public String getCookieValue() {
			return cookieValue;
		}

		public void setCookieValue(String cookieValue) {
			this.cookieValue = cookieValue;
		}

		public String getCookieName() {
			return cookieName;
		}

		public void setCookieName(String cookieName) {
			this.cookieName = cookieName;
		}
	}
}
