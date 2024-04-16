package com.cirbal.springboot.app.oauth.services;

import com.cirbal.springboot.app.commons.users.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);

	public User userUpdate(User user, Long id);
}
