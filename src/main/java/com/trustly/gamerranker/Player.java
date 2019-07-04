package com.trustly.gamerranker;

public class Player {

  private final String name;
  private final String game;
  private double rating;

  public Player(String name, String game) {
    this.name = name;
    this.game = game;
    rating = 1000.0;
  }

  public Player(String name, String game, double rating) {
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

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public void increaseRating(double increase) {
    rating += increase;
  }

  public void decreaseRating(double decrease) {
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
