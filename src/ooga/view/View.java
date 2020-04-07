package ooga.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View extends BorderPane {

  private static final double SCENE_WIDTH = 500;
  private static final double SCENE_HEIGHT = 500;

  public View(Stage stage) {
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    setCenter(new Board());
    stage.setScene(scene);
    stage.show();
  }
}
