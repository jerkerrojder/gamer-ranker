package com.trustly.gamerranker;

class EloCalculator {


  void updateElo(final Player winner, final Player loser) {
    final double ratingDifference = calculateRatingDifference(winner, loser);
    winner.increaseRating(ratingDifference);
    loser.decreaseRating(ratingDifference);
  }


  double calculateRatingDifference(final Player winner, final Player loser) {
    final double ratingDifference = winner.getRating() - loser.getRating();
    final double exponent = -(ratingDifference/400);
    final double scoreEstimation = 1 / (1 + Math.pow(10, exponent));
    return 70 * (1 - scoreEstimation);
  }
}
