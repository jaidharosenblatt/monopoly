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
import ooga.view.View;

public class SplashScreen {

  private Stage stage;
  private View view;
  private VBox vBox = new VBox();
  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";

  public SplashScreen(double width, double height, View view) {
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);


    vBox.setAlignment(Pos.CENTER);
    vBox.setId("decision-display");
    vBox.getChildren().add(new GameTypePicker(stage, view));

    Scene scene = new Scene(vBox, width, height);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }

}
