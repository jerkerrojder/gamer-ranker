package com.trustly.gamerranker.controller;

import com.trustly.gamerranker.data.Points;
import com.trustly.gamerranker.repository.PointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointController {
	private final PointsRepo pointsRepo;

	@Autowired
	public PointController(PointsRepo pointsRepo) {
		this.pointsRepo = pointsRepo;
	}

	@GetMapping("points")
	public List<Points> getPointsForGameId(@RequestParam("gameId") Long gameId) {
		return pointsRepo.getPointsForGameId(gameId);
	}
}
