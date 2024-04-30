package com.cirbal.springboot.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.cirbal.springboot.app.commons.users.models.entity.User;
import com.cirbal.springboot.app.oauth.services.IUserService;

@Component
public class InfoAdditionalToken implements TokenEnhancer {

	@Autowired
	private IUserService iUserService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> infoTokenMap = new HashMap<>();
		User user = iUserService.findByUsername(authentication.getName());
		infoTokenMap.put("Email", user.getEmail());
		infoTokenMap.put("Nombre", user.getPassword());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infoTokenMap);
		return accessToken;
	}

}
