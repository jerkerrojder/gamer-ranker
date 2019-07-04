package com.trustly.gamerranker.controller;

import com.trustly.gamerranker.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final UserRepo userRepo;

	@Autowired
	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@PostMapping("user")
	public void addUser(@RequestParam("username") String username) {
		userRepo.addUser(username);
	}
}
