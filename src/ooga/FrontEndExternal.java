package ooga;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import ooga.view.Decision;
import ooga.PlayerInfo;


public interface FrontEndExternal {

  /**
   * This method is used by the back end to prompt the player for an input to a set of responses. An
   * example of this is "Buy Property 1?" ,{"Yes","No"}
   *
   * @param decision the decision to present to the user
   * @return A list of indexes corresponding to the chosen responses by the user
   */
  List<Integer> makeUserDecision(Decision decision);

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
  void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers);

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param playerId  integer representation of the player
   * @param numSpaces amount of spaces the player needs to move
   */
  void movePlayer(int playerId, int numSpaces);

  /**
   * Use this to display the result of the dice roll
   *
   * @return a list of the value each die had
   */
  List<Integer> getRoll();

  /**
   * Use this method to change the theme of the board and the UI
   *
   * @param pathToThemePropertyFile the new Theme to change to
   */
  void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException;


}
