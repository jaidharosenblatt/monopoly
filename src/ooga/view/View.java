package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View extends HBox {

  /**
   * TODO
   * Add corners
   * Add actions display
   */

  private static final double SCENE_WIDTH = 700;
  private static final double SCENE_HEIGHT = 700;

  public View(Stage stage) {
    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    Group g = new Group(new Board());
    getChildren().add(g);

    stage.setScene(scene);
    stage.show();

  }
}
