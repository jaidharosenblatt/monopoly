package ooga;

import javafx.stage.Stage;
import ooga.view.View;

public class Controller {

  //this class will inject buttons into the view or get a return from the view and do the on action commands to the backend

  public Controller(Stage stage) {
    new View(stage);
  }

}
