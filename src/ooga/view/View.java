package ooga.view;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.LoadGame;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.FrontEndExternal;
import ooga.api.view.Decision;
import ooga.view.board.Board;
import ooga.view.gamedisplay.DecisionView;
import ooga.view.gamedisplay.GameDisplay;

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
  private LoadGame controller;
  private static final double SCENE_WIDTH = 900;
  private static final double SCENE_HEIGHT = 700;
  private List<Player> players;
  private Player currentPlayer;
  private Stage stage;

  public View(Stage stage, LoadGame controller, List<Player> players, List<Tile> tiles) {
    getStylesheets().add("resources/default.css");

    System.out.println(tiles);
    this.stage = stage;
    this.players = players;
    this.controller = controller;
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    board = new Board(players, tiles);
    Group boardGroup = new Group(board);

    gameDisplay = new GameDisplay(this);

    getChildren().addAll(boardGroup, gameDisplay);

    stage.setScene(scene);
    stage.show();
  }

  public Stage getStage() {
    return stage;
  }

  public void setCurrentPlayer(Player p){
    this.currentPlayer = p;
  }

  public void handleRoll(){
    controller.takeTurn();
  }

  public void handleMortgage(){
    System.out.println("Mortgage");
  }

  public void handleTrade(){
    System.out.println("Trade");
  }

  @Override
  public void makeUserDecision(Decision decision) {
    new DecisionView(decision);
  }

  @Override
  public void displayText(String text) {
    gameDisplay.displayText(text);
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
