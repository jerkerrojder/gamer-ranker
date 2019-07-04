package com.trustly.gamerranker;

public class Player {

  private final String name;
  private final String game;
  private double rating;

  Player(String name, String game) {
    this.name = name;
    this.game = game;
    rating = 1000.0;
  }

  Player(String name, String game, double rating) {
    this.name = name;
    this.game = game;
    this.rating = rating;
  }

  public String getName() {
    return name;
  }

  public String getGame() {
    return game;
  }

  double getRating() {
    return rating;
  }

  void setRating(double rating) {
    this.rating = rating;
  }

  void increaseRating(double increase) {
    rating += increase;
  }

  void decreaseRating(double decrease) {
    rating -= decrease;
  }

  @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        ", game='" + game + '\'' +
        ", rating=" + rating +
        '}';
  }
}
