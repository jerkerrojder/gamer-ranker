package com.trustly.gamerranker.repository;

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
		String query = "select id from users where username=?";
		return jdbcTemplate.queryForObject(query, Long.class, name);
	}
}
