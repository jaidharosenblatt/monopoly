package ooga.api.objects;

import java.util.List;

import javafx.scene.paint.Paint;
import ooga.view.board.PropertyView;

public interface PlayerInfo {

  /**
   * @return a List of unmodifiable properties that a player currently owns as well as any
   * houses/hotels
   */
  List<PropertyView> getPropertiesUnmodifiable();

  /**
   * @return amount of money the player has
   */
  Integer getCashBalance();

  /**
   * @return Where on the board they are
   */
  Integer getPositionOnBoard();

  int getPlayerId();

  /**
   *
   * @return the hex of the player's color in 0-255 integer standard, comma separate the values.
   */
  String getPlayerColor();

  String getName();

}
