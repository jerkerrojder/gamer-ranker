package com.trustly.gamerranker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EloCalculatorTest {

  private EloCalculator ec = new EloCalculator();
  private final Player player1 = new Player("Jerk", "Foosball");
  private final Player player2 = new Player("Steph", "Foosball");

  private DecimalFormat df = new DecimalFormat("#.####");

  @BeforeEach
  void setUp() {
    df.setRoundingMode(RoundingMode.CEILING);
  }

  @Test
  void winnersEloIncreases() {

    ec.updateElo(player2, player1);

    assertTrue(player2.getRating() > player1.getRating());
  }

  @Test
  void scoreDifferenceIsCorrect_WhenMatchUpIsUneven() {
    player1.setRating(1000);
    player2.setRating(1300);

    final double scoreDifference = ec.calculateRatingDifference(player1, player2);

    assertEquals(df.format(scoreDifference), df.format(59.43143));
  }
}