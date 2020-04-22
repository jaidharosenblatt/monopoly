package ooga.api.objects;

public interface Card {

  /**
   * @return the category of this card
   */
  String getCategory();

  /**
   * @return the state (used or not)
   */
  boolean isUsed();

  /**
   * Activate the card on a given player
   *
   * @return playerId the player to modify
   */
  int activate();
}
