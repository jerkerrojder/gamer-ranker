package com.trustly.gamerranker.data.mapper;

import com.trustly.gamerranker.data.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper {
	@Override
	public Game mapRow(ResultSet resultSet, int i) throws SQLException {
		Game game = new Game();
		game.setId(resultSet.getLong("id"));
		game.setGameName(resultSet.getString("gamename"));
		return game;
	}
}
