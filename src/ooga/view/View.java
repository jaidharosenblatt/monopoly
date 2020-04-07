package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

  public View(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root, 100, 100);
    stage.setScene(scene);
    stage.show();
  }
}
