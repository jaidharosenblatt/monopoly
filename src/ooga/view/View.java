package ooga.view;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.Controller;
import ooga.api.FrontEndExternal;
import ooga.api.view.Decision;
import ooga.api.view.PlayerInfo;
import ooga.view.board.Board;
import ooga.view.gamedisplay.GameDisplay;
import ooga.view.gamedisplay.decisions.DecisionTester;

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
  private Controller controller;
  private static final double SCENE_WIDTH = 1000;
  private static final double SCENE_HEIGHT = 700;
  private Map<PlayerInfo, Integer> playerPositions;
  TabView myTabView;
  private int rulesTabID;

  public View(Stage stage, Controller controller, Map<PlayerInfo, Integer> playerPositions) {
    this.controller = controller;
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    board = new Board(playerPositions);
    Group boardGroup = new Group(board);

    myTabView = new TabView(SCENE_WIDTH/3,SCENE_HEIGHT);
    rulesTabID = myTabView.createTab("Rules");
    myTabView.addTabPaneToView(this);
    myTabView.updateTab(rulesTabID, List.of("Rule1", "Rule2", "Rule3"));

    gameDisplay = new GameDisplay(this);

    getChildren().addAll(boardGroup, gameDisplay);
    scene.getStylesheets().add("resources/default.css");

    stage.setScene(scene);
    stage.show();
  }


  public void handleRoll(){
    controller.handleRoll();
  }

  public void submitDecision(List<String> decision) {
    controller.submitDecision(decision);
  }

  @Override
  public void makeUserDecision(Decision decision, boolean multiChoice) {
    gameDisplay.makeUserDecision(decision, multiChoice);
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
