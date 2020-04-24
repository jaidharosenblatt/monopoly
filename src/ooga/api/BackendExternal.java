package ooga.api;

import java.util.List;

import ooga.BackEnd.GameObjects.Player;
import ooga.api.objects.PlayerInfo;
import ooga.api.objects.Property;

public interface BackendExternal {

  /**
   * This method is used by the back end to update global variables
   * containing players and tiles based on rules and actions that
   * happened during a player's turn.
   *
   */

  void takeTurn();

  /**
   * This method is used by the back end to select the next player
   * in the rotation.
   *
   */

  void nextPlayer();

  /**
   * This method is used by the back end to roll the current player's
   * dice, move him or her to that spot, and trigger the action of
   * that tile.
   *
   */

  void rollDiceAndMove(Player p);

  /**
   * This method is used by the back end to trigger the House class
   * which allows for the player to build a house on a property if
   * he or she qualifies to do so.
   *
   */

  void buildHouse();

  /**
   * This method is used by the back end to trigger the House class
   * which allows for the player to sell a house on a property if
   * he or she qualifies to do so.
   *
   */

  void sellHouse();

  /**
   * This method is used by the back end to trigger the Mortgage class
   * which allows for the player to mortgage a property if he or she
   * qualifies to do so.
   *
   */

  void mortgageProp();

  /**
   * This method is used by the back end to trigger the Mortgage class
   * which allows for the player to life the mortgage off of a property
   * if he or she qualifies to do so.
   *
   */

  void unmortgageProp();

  /**
   * This method is used by the back end to trigger the Trade class
   * which allows for the player to trade with another player.
   *
   */

  void trade();
}
