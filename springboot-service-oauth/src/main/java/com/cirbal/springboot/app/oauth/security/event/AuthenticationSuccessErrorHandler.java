package com.cirbal.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.cirbal.springboot.app.commons.users.models.entity.User;
import com.cirbal.springboot.app.oauth.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationEventPublisher.class);

	@Autowired
	private IUserService iUserService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
// (authentication.getName().equalsIgnoreCase("client_id_username"))
		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String messageString = "***********************\n" + "Success Login \n" + "**************************"
				+ userDetails.getUsername();
		System.out.println(messageString);
		log.info(messageString);
		User user = iUserService.findByUsername(authentication.getName());
		if (user.getIntentInteger() != null && user.getIntentInteger() > 0) {
			user.setIntentInteger(0);
			iUserService.userUpdate(user, user.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String messageString = " ERROR LOGIN ERROR" + exception.getMessage();
		System.out.println(messageString);
		log.error(messageString);

		try {
			User user = iUserService.findByUsername(authentication.getName());

			if (user == null) {
				if (user.getIntentInteger() == null) {
					user.setIntentInteger(0);
				}
				log.info("Intentos Actuales ----------------: " + user.getIntentInteger());
				user.setIntentInteger(user.getIntentInteger() + 1);
				log.info("Intentos Posterior ---------------: " + user.getIntentInteger());

				if (user.getIntentInteger() >= 3) {
					user.setEnable(false);
					log.error(String.format("*******************************************************\n"
							+ "**************El usuario %s esta desahibilitado*******************\n"
							+ user.getUsername()));

				}
				iUserService.userUpdate(user, user.getId());
			}
		} catch (FeignException e) {
			log.error(String.format("////////User %s Don't Exist/////////", authentication.getName()));
		}
	}
}
