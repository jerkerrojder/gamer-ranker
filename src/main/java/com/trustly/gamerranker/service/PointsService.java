package com.trustly.gamerranker.service;

import com.trustly.gamerranker.data.Points;
import com.trustly.gamerranker.repository.PointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsService {
	private final PointsRepo pointsRepo;

	@Autowired
	public PointsService(PointsRepo pointsRepo) {
		this.pointsRepo = pointsRepo;
	}

	public void updatePointsForUser(Long gameId, Long userId, Double deltaPoints) {
		Points points = pointsRepo.getPointsForGameIdAndUserId(gameId, userId);
		if (points == null) {
			pointsRepo.addPoints(gameId, userId, deltaPoints);
		} else {
			Double calculatedPoint = calculatePoint(deltaPoints, points.getPoints());
			pointsRepo.updatePoints(gameId, userId, calculatedPoint);
		}
	}

	private Double calculatePoint(Double deltaPoints, double points) {
		return deltaPoints;
	}
}
