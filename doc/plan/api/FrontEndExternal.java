
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import view.Decision;
import view.PlayerInfo;

public interface FrontEndExternal {

  /**
   * This method is used by the back end to prompt the player for an input to a set of responses. An
   * example of this is "Buy Property 1?" ,{"Yes","No"}
   *
   * @param decision the decision to present to the user
   * @return The index corresponding to the chosen response by the user
   */
  public int makeUserDecision(Decision decision);

  /**
   * Use this method to refresh the players display after a turn has been completed. Specifically it
   * will update the assets of the players.
   *
   * @param currentPlayers This Map holds all of the current asset information for the players
   */
  public void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers);

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param playerId  integer representation of the player
   * @param numSpaces amount of spaces the player needs to move
   */
  public void movePlayer(int playerId, int numSpaces);

  /**
   * Use this to display the result of the dice roll
   *
   * @param diceResults a list of the value each die had
   */
  public void rollDice(List<Integer> diceResults);

  /**
   * Use this method to change the theme of the board and the UI
   *
   * @param pathToThemePropertyFile the new Theme to change to
   */
  public void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException;


}
