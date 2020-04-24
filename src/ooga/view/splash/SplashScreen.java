package ooga.view.splash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.Main;
import ooga.util.PropertiesGetter;
import ooga.view.error.ErrorView;

/**
 * @author jaidharosenblatt Screen to begin the game and prompt user to give players details and
 * game type Controls and is depedent on dropdowns for game type, selecting number of players, and
 * player info
 */
public class SplashScreen {

  private Stage stage;
  private VBox root = new VBox();
  private VBox playersBox = new VBox();
  private List<PlayerSetter> playersList = new ArrayList<>();

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


  /**
   * Constructor
   *
   * @param main instance of main class in order to call the creation of the backend/frontend with
   *             settings from this class
   */
  public SplashScreen(Main main) {
    this.controller = main;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    ImageView title = getTitleImage();

    root.setId("splash");
    NumberPlayersDropdown dropdown = new NumberPlayersDropdown(DEFAULT_NUMBER_OF_PLAYERS,
        MIN_NUMBER_OF_PLAYERS, MAX_NUMBER_OF_PLAYERS, this);

    GameTypePicker picker = new GameTypePicker(PropertiesGetter.getPromptFromKey("BoardType"),
        this);
    root.getChildren().addAll(title, dropdown, playersBox, picker);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }


  protected void submit(String boardPath) {
    try {
      controller.startGame(getPlayerInfo(), boardPath);
    } catch (IndexOutOfBoundsException e ){
      new ErrorView("NullPlayer");
      new SplashScreen(controller);
    }
    catch (Exception e) {
      new ErrorView("LoadGame");
    }
  }

  private Map<String, String> getPlayerInfo() {
    Map<String, String> playerInfo = new HashMap<>();
    for (PlayerSetter player : playersList) {
      playerInfo.put(player.getName(), player.getColor());
    }
    return playerInfo;
  }

  protected void closeStage() {
    stage.close();
  }

  protected void setNumPlayers(int numPlayers) {
    createPlayersBox(numPlayers);
  }

  protected void createPlayersBox(int numPlayers) {
    playersBox.getChildren().clear();
    for (int i = 1; i <= numPlayers; i++) {
      PlayerSetter player = new PlayerSetter(PropertiesGetter.getPromptFromKey("PlayerColor"),
          PropertiesGetter.getPromptFromKey("Name"),
          PropertiesGetter.getPromptFromKey("Player") + i);
      playersBox.getChildren().add(player);
      playersList.add(player);
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
