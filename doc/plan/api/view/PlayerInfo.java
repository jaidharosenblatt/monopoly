package view;

import java.util.List;

public interface PlayerInfo {

  /**
   * Add a property to this player
   *
   * @param propertyId the id for the new property
   */
  public void buyProperty(int propertyId);

  /**
   * @return a List of properties that a player currently owns as well as any houses/hotels
   */
  public List<Property> getPropertiesUnmodifiable();

  /**
   * @return a List of cards that a player currently owns
   */
  public List<Card> getHeldCardsUnmodifiable();

  /**
   * @return amount of money the player has
   */
  public int getCashBalance();

  /**
   * @return Where on the board they are
   */
  public int getPositionOnBoard();
}
