package ooga.view.splash;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.view.View;

public class SplashScreen {

  private Stage stage;
  private View view;
  private VBox vBox = new VBox();
  private int numPlayers = DEFAULT_NUMBER_OF_PLAYERS;
  private static final int DEFAULT_NUMBER_OF_PLAYERS = 4;
  private static final int MAX_NUMBER_OF_PLAYERS = 6;
  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";

  public SplashScreen(double width, double height, View view) {
    this.view = view;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    vBox.setAlignment(Pos.CENTER);
    vBox.setId("decision-display");
    vBox.getChildren()
        .add(new NumberPlayersDropdown(DEFAULT_NUMBER_OF_PLAYERS, MAX_NUMBER_OF_PLAYERS, this));
    vBox.getChildren().add(new GameTypePicker(this));

    Scene scene = new Scene(vBox, width, height);
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
    this.numPlayers = numPlayers;
    System.out.println(numPlayers);
  }

}
