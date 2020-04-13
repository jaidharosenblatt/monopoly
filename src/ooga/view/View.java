package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View extends Pane {

  /**
   * TODO
   * Fix utlitytile not moving when initially added
   * Add corners
   * Add actions display
   */

  private static final double SCENE_WIDTH = 700;
  private static final double SCENE_HEIGHT = 700;

  public View(Stage stage) {
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    getChildren().add(new Board());

    stage.setScene(scene);
    stage.show();

  }
}
