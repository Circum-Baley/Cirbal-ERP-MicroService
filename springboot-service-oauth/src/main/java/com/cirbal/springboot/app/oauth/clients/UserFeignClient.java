package com.cirbal.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirbal.springboot.app.commons.users.models.entity.User;

@FeignClient("service-users")
public interface UserFeignClient {

	@GetMapping("/users/search/find-username")
	public User findUserByUsername(@RequestParam String username);

	@PostMapping("/createUser")
	public User create(User user);

	@PutMapping("/userE dit/{id}")
	public User edit(@RequestParam Long id, User us);

	@DeleteMapping("/userDelete/{id}")
	public void delete(@RequestParam Long id);
}
