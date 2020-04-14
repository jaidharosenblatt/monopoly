package ooga.view;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.api.FrontEndExternal;
import ooga.api.view.Decision;
import ooga.api.view.PlayerInfo;
import ooga.view.board.Board;

public class View extends HBox implements FrontEndExternal {

  /**
   * TODO Add actions display
   */

  private Board board;
  private DecisionView choicesDisplay;
  private static final double SCENE_WIDTH = 1000;
  private static final double SCENE_HEIGHT = 700;

  public View(Stage stage) {
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    board = new Board();
    Group boardGroup = new Group(board);

    Decision d = new DecisionTester("oops", List.of("choice 1", "choice 2", "choice 3"));
    Method m = null;
    try {
      m = getClass().getDeclaredMethod("submitDecision", List.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    choicesDisplay = new DecisionView(d, m);

    getChildren().addAll(boardGroup, choicesDisplay);
    scene.getStylesheets().add("resources/default.css");

    stage.setScene(scene);
    stage.show();
  }

  public void submitDecision(List<Integer> decision) {
    System.out.println(decision);
  }

  @Override
  public List<Integer> makeUserDecision(Decision decision) {
    return null;
  }

  @Override
  public void displayText(String text) {

  }

  @Override
  public void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers) {

  }

  @Override
  public void movePlayer(PlayerInfo player, int position) {
    board.movePlayer(player, position);
  }

  @Override
  public void displayRoll(List<Integer> rolls) {

  }

  @Override
  public void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException {

  }
}
