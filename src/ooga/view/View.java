package ooga.view;

import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.LoadGame;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.FrontEndExternal;
import ooga.api.objects.Decision;
import ooga.api.objects.MultiPlayerDecision;
import ooga.api.objects.MultiPropDecision;
import ooga.api.objects.PlayerInfo;
import ooga.api.objects.StringDecision;
import ooga.view.board.ClassicBoard;
import ooga.view.gamedisplay.*;
import ooga.view.tabs.TabView;

/**
 * @author jaidharosenblatt Controller for the main window for the frontend. Implements the frontend
 * API and has all methods related to the frontend (outside of errors and the splash screen). Holds
 * an instance of the backend in order to directly call methods without a use of a controller.
 * Depedent on every Class in the frontend outside of SplashScreen and ErrorView
 */
public class View extends BorderPane implements FrontEndExternal {

  public static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private ClassicBoard board;
  private TurnActionButtons gameDisplay;
  private LoadGame controller;
  private static final double SCENE_WIDTH = 1000;
  private static final double SCENE_HEIGHT = 735;
  private PlayerInfo currentPlayer;
  private TabView tabView;

  /**
   * Constructs a new view
   *
   * @param stage      the primary stage to add our view to
   * @param controller an instance of the backend
   * @param players    read-only details about the players
   * @param tiles      tiles that make up a board
   */
  public View(Stage stage, LoadGame controller, List<PlayerInfo> players, List<Tile> tiles) {
    this.controller = controller;

    getStylesheets().add(RESOURCES_DEFAULT_CSS);

    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);

    board = new ClassicBoard(players, tiles);
    Group boardGroup = new Group(board);
    gameDisplay = new TurnActionButtons(this);
    Group tabGroup = new Group();
    tabView = new TabView(SCENE_WIDTH / 3, SCENE_HEIGHT * 0.95);
    tabView.initializeProperties(tiles);
    tabView.addTabPaneToGroup(tabGroup);

    setLeft(boardGroup);
    setTop(gameDisplay);
    setRight(tabGroup);

    stage.setScene(scene);
    stage.show();
  }


  /**
   * Tells the backend that user started a new turn
   */
  public void handleRoll() {
    controller.takeTurn();
  }

  /**
   * Tells the backend that user wants to build houses
   */
  public void handleBuild() {
    controller.buildHouse();
  }

  /**
   * Tells the backend that user wants to sell houses
   */
  public void handleSell() {
    controller.sellHouse();
  }

  /**
   * Tells the backend that user wants to mortgage a property
   */
  public void handleMortgage() {
    controller.mortgageProp();
  }

  /**
   * Tells the backend that user wants to unmortgage a property
   */
  public void handleUnmortgage() {
    controller.unmortgageProp();
  }

  /**
   * Tells the backend that user wants to trade
   */
  public void handleTrade() {
    controller.trade();
  }

  /**
   * Prompt a user and get a answer from a text input
   *
   * @param decision the decision to present to the user
   */
  @Override
  public void makeStringDecision(StringDecision decision) {
    new StringDecisionView(decision, currentPlayer.getName(),
        Color.web(currentPlayer.getPlayerColor()));
  }

  /**
   * Update the current player in the frontend
   *
   * @param p the player whose turn it is
   */
  @Override
  public void setCurrentPlayer(Player p) {
    this.currentPlayer = p;
  }


  /**
   * This method is used by the back end to prompt the player for an input to a set of responses. An
   * example of this is "Buy Property 1?" ,{"Yes","No"}
   *
   * @param decision the decision to present to the user
   */
  @Override
  public void makeUserDecision(Decision decision) {
    new DecisionView(decision, currentPlayer.getName(), Color.web(currentPlayer.getPlayerColor()));
  }

  /**
   * This method is used by the back end to prompt the player for an input to a set of responses. An
   * example of this is "Which Property?" ,{"Baltic Avenue","Mediterranean Avenue", "Oriental
   * Avenue"}
   *
   * @param decision the decision to present to the user
   */
  @Override
  public void makeMultiDecision(MultiPropDecision decision) {
    new MultiPropDecisionView(decision, currentPlayer.getName(),
        Color.web(currentPlayer.getPlayerColor()));
  }

  /**
   * This method is used by the back end to prompt the player for an input to a set of responses. An
   * example of this is "Which Player?" ,{"Player 1","Player 2", "Player 3"}
   *
   * @param decision the decision to present to the user
   */
  @Override
  public void makeMultiPlayerDecision(MultiPlayerDecision decision) {
    new MultiPlayerDecisionView(decision, currentPlayer.getName(),
        Color.web(currentPlayer.getPlayerColor()));
  }

  /**
   * Use this method to refresh the players display after a turn has been completed. Specifically it
   * will update the assets of the players.
   *
   * @param currentPlayers This Map holds all of the current asset information for the players
   */
  @Override
  public void refreshPlayers(Map<Integer, Player> currentPlayers) {
    tabView.updatePlayersTab(currentPlayers);
  }

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param player   player to move
   * @param position position on board to move to
   */
  @Override
  public void movePlayer(PlayerInfo player, int position) {
    board.movePlayer(player, position);
  }

  /**
   * Use this to display the result of the dice roll
   *
   * @return a list of the value each die had
   */
  @Override
  public void displayRoll(List<Integer> rolls) {
    board.displayRoll(rolls);
  }

}
