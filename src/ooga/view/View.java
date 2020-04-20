package ooga.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.LoadGame;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
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
  private LoadGame controller;
  private static final double SCENE_WIDTH = 900;
  private static final double SCENE_HEIGHT = 700;
  private List<Player> players;
  private Player currentPlayer;

  public View(Stage stage, LoadGame controller, List<Player> players, List<Tile> tiles) {
    this.players = players;
    this.controller = controller;
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    board = new Board(players);
    Group boardGroup = new Group(board);

    gameDisplay = new GameDisplay(this);

    getChildren().addAll(boardGroup, gameDisplay);
    scene.getStylesheets().add("resources/default.css");

    stage.setScene(scene);
    stage.show();
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

  public void submitDecision(List<String> decision) {
//    controller.submitDecision(decision);
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
