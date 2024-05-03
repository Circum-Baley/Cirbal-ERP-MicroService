package com.cirbal.springboot.app.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.cirbal.springboot.app.commons.users.models.entity.User;
import java.util.List;
import java.util.Optional;


@RepositoryRestResource(path = "users")
public interface UserDAO extends PagingAndSortingRepository<User, Long> {

	@RestResource(path = "find-username")
	public User findByUsername(@Param("username") String username);

	@Query("select u from User u where u.username=?1")
	public User getByUsername(String username);
}
