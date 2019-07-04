package com.trustly.gamerranker.data;

import lombok.Data;

@Data
public class Match {
	private long user1Id;
	private long user2Id;

	private int scoreUser1;
	private int scoreUser2;

	private long gameId;
}
