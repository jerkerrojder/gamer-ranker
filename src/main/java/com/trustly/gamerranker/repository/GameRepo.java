package com.trustly.gamerranker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GameRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveGame(String gameName) {
		String query = "insert into games (gamename) values (?) ";
		jdbcTemplate.update(query, gameName);
	}
}
