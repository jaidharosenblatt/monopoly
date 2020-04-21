package ooga.view;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.LoadGame;
import ooga.BackEnd.GameLogic.MultiDecision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.FrontEndExternal;
import ooga.api.view.Decision;
import ooga.view.board.Board;
import ooga.view.gamedisplay.DecisionView;
import ooga.view.gamedisplay.MultiDecisionView;
import ooga.view.gamedisplay.TurnActionButtons;
import ooga.view.tabs.TabView;

public class View extends BorderPane implements FrontEndExternal {

  public static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private Board board;
  private TurnActionButtons gameDisplay;
  private LoadGame controller;
  private static final double SCENE_WIDTH = 900;
  private static final double SCENE_HEIGHT = 700;
  private List<Player> players;
  private Player currentPlayer;
  private TabView tabView;

  public View(Stage stage, LoadGame controller, List<Player> players, List<Tile> tiles) {
    this.players = players;
    this.controller = controller;

    getStylesheets().add(RESOURCES_DEFAULT_CSS);

    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);

    board = new Board(players, tiles);
    Group boardGroup = new Group(board);
    gameDisplay = new TurnActionButtons(this);
    Group tabGroup = new Group();
    tabView = new TabView(SCENE_WIDTH / 3, SCENE_HEIGHT);
    tabView.addTabPaneToGroup(tabGroup);

    setLeft(boardGroup);
    setTop(gameDisplay);
    setRight(tabGroup);

    stage.setScene(scene);
    stage.show();
  }

  public void setCurrentPlayer(Player p) { this.currentPlayer = p;}

  public void handleRoll() {controller.takeTurn();}

  public void handleBuild() {controller.build();}

  public void handleSell() {controller.sell();}

  public void handleMortgage() {controller.mortgage();}

  public void handleUnmortgage() {controller.unmortgage();}

  public void handleTrade() {
    System.out.println("Trade");
  }

  @Override
  public void makeUserDecision(Decision decision) {
    new DecisionView(decision, currentPlayer.getName(), Color.NAVY);

//    new DecisionView(decision, currentPlayer.getName(), Color.web(currentPlayer.getPlayerColor()));
  }

  @Override
  public void makeMultiDecision(MultiDecision decision) {
    new MultiDecisionView(decision, currentPlayer.getName(), Color.NAVY);
  }

  @Override
  public void displayText(String text) {
  }

  @Override
  public void refreshPlayers(Map<Integer, Player> currentPlayers) {

  }

  @Override
  public void movePlayer(Player player, int position) {
    board.movePlayer(player, position);
  }

  @Override
  public void displayRoll(List<Integer> rolls) {
    board.displayRoll(rolls);
  }

  @Override
  public void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException {

  }
}
