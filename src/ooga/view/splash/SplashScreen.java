package ooga.view.splash;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.stream.XMLStreamException;
import ooga.Main;
import ooga.view.View;

public class SplashScreen {

  private Stage stage;
  private VBox root = new VBox();
  private VBox players = new VBox();

  private Map<String, String> playerInfo = new HashMap<>();

  private Main controller;
  private static final int DEFAULT_NUMBER_OF_PLAYERS = 4;
  private static final int MIN_NUMBER_OF_PLAYERS = 2;
  private static final int MAX_NUMBER_OF_PLAYERS = 6;
  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private static final String IMAGE_PATH = "monopoly.png";
  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 600;


  private static final double IMAGE_WIDTH = 400;
  private static final double IMAGE_HEIGHT = 100;


  public SplashScreen(Main main) {
    this.controller = main;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    ImageView title = getTitleImage();

    root.setAlignment(Pos.CENTER);
    root.setId("decision-display");
    NumberPlayersDropdown dropdown = new NumberPlayersDropdown(DEFAULT_NUMBER_OF_PLAYERS,
        MIN_NUMBER_OF_PLAYERS, MAX_NUMBER_OF_PLAYERS, this);

    GameTypePicker picker = new GameTypePicker("Board type", this);
    root.getChildren().addAll(title, dropdown, players, picker);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }


  protected void submit(String boardPath) {
    try {
      controller.startGame(playerInfo,boardPath);
    } catch (FileNotFoundException e) {
      System.out.println("no file");
    } catch (XMLStreamException e) {
      System.out.println("no XML");
    }
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

  private ImageView getTitleImage() {
    Image i = new Image(this.getClass().getClassLoader().getResourceAsStream(IMAGE_PATH));
    ImageView imageView = new ImageView(i);
    imageView.setFitWidth(IMAGE_WIDTH);
    imageView.setFitHeight(IMAGE_HEIGHT);

    return imageView;
  }

}
