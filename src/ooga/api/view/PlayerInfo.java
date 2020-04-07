package ooga.api.view;

import java.util.List;
import ooga.api.view.Card;
import ooga.api.view.Property;

public interface PlayerInfo {

  /**
   * @return a List of unmodifiable properties that a player currently owns as well as any
   * houses/hotels
   */
  List<Property> getPropertiesUnmodifiable();

  /**
   * @return a List of cards that a player currently owns
   */
  List<Card> getHeldCardsUnmodifiable();

  /**
   * @return amount of money the player has
   */
  Integer getCashBalance();

  /**
   * @return Where on the board they are
   */
  Integer getPositionOnBoard();

  int getPlayerId();
}
