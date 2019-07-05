package com.trustly.gamerranker.repository;

import com.trustly.gamerranker.data.Game;
import com.trustly.gamerranker.data.mapper.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserRepo(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void addUser(String username) {
    String query = "insert into users (username) values (?)";
    jdbcTemplate.update(query, username);
  }

  public Long getUserIdFromName(String name) {
    String query = "select id from users where username=" + name;
    return jdbcTemplate.queryForObject(query, Long.class);
  }

  public List<Game> getAllUsers() {
    String query = "select * from users";
    return jdbcTemplate.query(query, new UserMapper());
  }
}
