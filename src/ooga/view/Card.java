package ooga.view;

public interface Card {

  /**
   * @return the category of this card
   */
  public String getCategory();

  /**
   * @return the state (used or not)
   */
  public boolean isUsed();

  /**
   * Activate the card on a given player
   *
   * @param playerId the player to modify
   */
  public void activate(int playerId);
}
