package com.trustly.gamerranker.rating;

import com.trustly.gamerranker.Player;

public interface RatingCalculator {

  void updateRating(Player winner, Player loser);

  double calculateRatingDifference(Player winner, Player loser);
}
