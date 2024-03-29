package com.trustly.gamerranker.repository;

import com.trustly.gamerranker.data.Game;
import com.trustly.gamerranker.data.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepo {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GameRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveGame(String gameName) {
		String query = "insert into games (gamename) values (?)";
		jdbcTemplate.update(query, gameName);
	}

	public List<Game> getAllgames() {
		String query = "select id, gamename from games";
		return jdbcTemplate.query(query, new GameMapper());
	}
}
