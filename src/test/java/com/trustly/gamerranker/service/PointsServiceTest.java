package com.trustly.gamerranker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trustly.gamerranker.data.Points;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointsServiceTest {

  private DecimalFormat df = new DecimalFormat("#.####");
  private final Points user1Points = new Points();
  private final Points user2Points = new Points();

  @BeforeEach
  void setUp() {
    df.setRoundingMode(RoundingMode.CEILING);

    user1Points.setGameId(11);
    user1Points.setUserId(1);
    user1Points.setPoints(1000);

    user2Points.setGameId(11);
    user2Points.setUserId(2);
    user2Points.setPoints(1300);
  }

  @Test
  void calculateCorrectDelta() {
    final Double delta = PointsService.calculateDelta(user1Points, user2Points);

    assertEquals(df.format(delta), df.format(59.43143));
  }
}