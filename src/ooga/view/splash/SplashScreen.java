package ooga.view.splash;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.view.View;

public class SplashScreen {

  private Stage stage;
  private View view;
  private VBox root = new VBox();
  private VBox players = new VBox();

  private Map<String, String> playerInfo = new HashMap<>();

  private static final int DEFAULT_NUMBER_OF_PLAYERS = 4;
  private static final int MIN_NUMBER_OF_PLAYERS = 2;
  private static final int MAX_NUMBER_OF_PLAYERS = 6;
  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";

  public SplashScreen(double width, double height, View view) {
    this.view = view;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    root.setAlignment(Pos.CENTER);
    root.setId("decision-display");
    NumberPlayersDropdown dropdown = new NumberPlayersDropdown(DEFAULT_NUMBER_OF_PLAYERS,
        MIN_NUMBER_OF_PLAYERS, MAX_NUMBER_OF_PLAYERS, this);

    GameTypePicker picker = new GameTypePicker("Board type", this);
    root.getChildren().addAll(dropdown, players, picker);

    Scene scene = new Scene(root, width, height);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }

  protected void setGameType(String boardPath) {
    view.setGameType(boardPath);
  }

  protected void closeStage() {
    stage.close();
  }

  protected void setNumPlayers(int numPlayers) {
    createPlayersBox(numPlayers);
  }

  protected void createPlayersBox(int numPlayers) {
    players.getChildren().clear();
    for (int i = 1; i <= numPlayers; i++) {
      PlayerSetter player = new PlayerSetter("Player Color", "Name", "Player " + i);
      players.getChildren().add(player);
      playerInfo.put(player.getName(), player.getColor());
    }
  }

}
