package ooga.api;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import ooga.BackEnd.GameObjects.Player;
import ooga.api.view.Decision;
import ooga.api.view.PlayerInfo;


public interface FrontEndExternal {

  /**
   * This method is used by the back end to prompt the player for an input to a set of responses. An
   * example of this is "Buy Property 1?" ,{"Yes","No"}
   *
   * @param decision the decision to present to the user
   * @param multiChoice whether the use can select multiple choices
   */
  void makeUserDecision(Decision decision, boolean multiChoice);

  /**
   * Display text to alert player
   *
   * @param text to display
   */
  void displayText(String text);

  /**
   * Use this method to refresh the players display after a turn has been completed. Specifically it
   * will update the assets of the players.
   *
   * @param currentPlayers This Map holds all of the current asset information for the players
   */
  void refreshPlayers(Map<Integer, Player> currentPlayers);

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param player player to move
   * @param position position on board to move to
   */
  void movePlayer(Player player, int position);

  /**
   * Use this to display the result of the dice roll
   *
   * @return a list of the value each die had
   */
  void displayRoll(List<Integer> rolls);

  /**
   * Use this method to change the theme of the board and the UI
   *
   * @param pathToThemePropertyFile the new Theme to change to
   */
  void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException;


}
