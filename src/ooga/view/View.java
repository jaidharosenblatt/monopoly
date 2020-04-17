package ooga.view;

import java.io.FileNotFoundException;
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
   * TODO
   * Add 2 action display
   * No choice display
   * Dice rolls
   * Player display
   */

  private Board board;
  private GameDisplay gameDisplay;
  private static final double SCENE_WIDTH = 1000;
  private static final double SCENE_HEIGHT = 700;

  public View(Stage stage) {
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    board = new Board();
    Group boardGroup = new Group(board);


    gameDisplay = new GameDisplay(this);

    Decision d = new DecisionTester("oops", List.of("choice 1", "choice 2", "choice 3"));
    makeUserDecision(d);

    getChildren().addAll(boardGroup, gameDisplay);
    scene.getStylesheets().add("resources/default.css");

    stage.setScene(scene);
    stage.show();
  }

  public void submitDecision(List<String> decision) {
    System.out.println("decision is: " + decision);
  }

  @Override
  public void makeUserDecision(Decision decision) {
    gameDisplay.makeUserDecision(decision);
  }

  @Override
  public void displayText(String text) {
    gameDisplay.displayText(text);
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
    gameDisplay.displayRoll(rolls);
  }

  @Override
  public void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException {

  }
}
