package ooga.view.gamedisplay;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SplashScreen {
  private Stage stage;
  private VBox vBox = new VBox();
  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private Map<String, String> gameTypes = new HashMap<>();

  public SplashScreen(double width, double height) {
    createGameTypes();

    System.out.println(gameTypes);
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    vBox.setAlignment(Pos.CENTER);
    vBox.setId("decision-display");

    Scene scene = new Scene(vBox,width,height);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }

  private void createGameTypes(){
    File[] f = new File("data").listFiles();
    for (File file : f) {
      if (file.isFile() && !file.getName().equals("FOLDER_PURPOSE.md")) {
        String display = file.getName().split("\\.")[0];
        String path = file.getPath();
        gameTypes.put(display,path);
      }
    }
  }
}
