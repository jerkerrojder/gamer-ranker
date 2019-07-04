package com.trustly.gamerranker.data;

import lombok.Data;

@Data
public class Points {
	private long gameId;
	private long userId;
	private double points;
}
