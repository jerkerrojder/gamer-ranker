package com.trustly.gamerranker.data.mapper;


import com.trustly.gamerranker.data.Points;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PointsMapper implements RowMapper {
	@Override
	public Points mapRow(ResultSet resultSet, int i) throws SQLException {
		Points points = new Points();
		points.setGameId(resultSet.getLong("gamesid"));
		points.setUserId(resultSet.getLong("usersid"));
		points.setPoints(resultSet.getDouble("player_points"));
		return points;
	}
}
