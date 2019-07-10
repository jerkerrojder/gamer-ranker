package com.trustly.gamerranker.service;

import com.trustly.gamerranker.data.Points;
import com.trustly.gamerranker.repository.PointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsService {

    public static final double STARTING_POINTS = 1000;
    private final PointsRepo pointsRepo;

    @Autowired
    public PointsService(PointsRepo pointsRepo) {
        this.pointsRepo = pointsRepo;
    }

    public void updatePoints(Long gameId, Long winnerId, Long loserId) {
        Points winnerPoints = pointsRepo.getPointsForGameIdAndUserId(gameId, winnerId);
        Points loserPoints = pointsRepo.getPointsForGameIdAndUserId(gameId, loserId);

        final Double deltaPoints = calculateDelta(winnerPoints, loserPoints);

        updatePointsForUser(gameId, winnerId, deltaPoints);
        updatePointsForUser(gameId, loserId, 0.0 - deltaPoints);
    }

    private void updatePointsForUser(Long gameId, Long userId, Double deltaPoints) {
        Points points = pointsRepo.getPointsForGameIdAndUserId(gameId, userId);
        if (points.getPoints() == 0.0) {
            pointsRepo.addPoints(gameId, userId, STARTING_POINTS + deltaPoints);
        } else {
            Double calculatedPoint = deltaPoints + points.getPoints();
            pointsRepo.updatePoints(gameId, userId, calculatedPoint);
        }
    }

    static Double calculateDelta(Points winnerPoints, Points loserPoints) {
        final double ratingDifference = winnerPoints.getPoints() - loserPoints.getPoints();
        final double exponent = -(ratingDifference / 400);
        final double scoreEstimation = 1 / (1 + Math.pow(10, exponent));
        return 70 * (1 - scoreEstimation);
    }
}
