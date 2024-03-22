package com.pi.controllers;

import java.util.List;

import com.pi.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.pi.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Slf4j
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@QueryMapping
	public List<User> getAllUsers(){
		return userService.selectall();
	}
	
	@QueryMapping
	public User getUser(@Argument int id){
		return userService.selectOne(id);
	}

/*	@MutationMapping
	public User createUser(@Argument User user, @Argument Long roleId, @Argument Long serviceDepId) {
		log.info("User: {}", user);

		return userService.addUser(user, roleId ,serviceDepId);
	}*/

	@MutationMapping
	public User updateUser(@Argument Long id, @Argument User user) {
		return userService.updateUser(id, user);
	}

	@MutationMapping
	public void deleteUser(@Argument Long id) {
		userService.deleteUser(id);
	}
}
