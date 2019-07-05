package com.trustly.gamerranker.controller;

import com.trustly.gamerranker.data.Game;
import com.trustly.gamerranker.repository.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("user")
  public List<Game> getAllUsers() {
    return userRepo.getAllUsers();
  }

  @GetMapping("getuserid")
  public Long getUserId(@RequestParam("username") String username) {
    return userRepo.getUserIdFromName(username);
  }
}
