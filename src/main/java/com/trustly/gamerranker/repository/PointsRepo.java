package com.trustly.gamerranker.repository;

import com.trustly.gamerranker.data.Points;
import com.trustly.gamerranker.data.mapper.PointsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointsRepo {

	public static final int STARTING_POINTS = 1000;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PointsRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Points> getPointsForGameId(Long gameId) {
		String query = "select * from points where gamesid=?";
		return jdbcTemplate.query(query, new PointsMapper(), gameId);
	}

	public Points getPointsForGameIdAndUserId(Long gameId, Long userId) {
		String query = "select * from points where gamesid=? and usersid=?";
		return (Points) jdbcTemplate.queryForObject(query, new PointsMapper(), gameId, userId);
	}

	public void addPoints(Long userId) {
		String query = "insert into points (gamesid,userid,player_points) values (?,?,?)";
		jdbcTemplate.update(query, 1, userId, STARTING_POINTS);
	}

	public void updatePoints(Long gameId, Long userId, Double playerPoints) {
		String query = "update points set player_points =?  where gamesid=? and userid=?";
		jdbcTemplate.update(query, playerPoints, gameId, userId);
	}
}