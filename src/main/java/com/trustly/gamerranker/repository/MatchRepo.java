package com.trustly.gamerranker.repository;

import com.trustly.gamerranker.data.Match;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepo {
	private final JdbcTemplate jdbcTemplate;

	public MatchRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveMatch(Match match) {
		String query = "insert into matches (gamesid,user1id,user2id,scoreuser1,scoreuser2) values (?,?,?,?,?)";
		jdbcTemplate.update(query, match.getGameId(), match.getUser1Id(), match.getScoreUser2(), match.getScoreUser1(), match.getScoreUser2());
	}
}
