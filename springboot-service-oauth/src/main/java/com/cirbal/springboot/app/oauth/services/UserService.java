package com.cirbal.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cirbal.springboot.app.commons.users.models.entity.User;
import com.cirbal.springboot.app.oauth.clients.UserFeignClient;

@Service
public class UserService implements IUserService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Error, User don't exists;");
		}
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role :" + authority.getAuthority())).collect(Collectors.toList());
		logger.info("User" + username + "Authenticated");
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getEnable(), true, true, true, authorities);
	}

	@Override
	public User findByUsername(String username) {
		try {
			User userF = userFeignClient.findByUsername(username);
			if (userF.getUsername() == null || userF.getUsername().isEmpty()) {
				throw new IllegalArgumentException("The User With Name :" + username + " Not Exists");
			}
			return userF;
		} catch (IllegalArgumentException Il) {
			logger.info("Illegal Argument Exception : " + Il.getMessage());
		} catch (Exception e) {
			logger.info("Error En el findByUsername" + e.getMessage());
		} catch (Throwable t) {
			logger.info("Unexpected Error Occurred : " + t.getMessage());
		}
		return null;
	}

	@Override
	public User userUpdate(User user, Long id) {
		return userFeignClient.updateUser(id, user);
	}
}