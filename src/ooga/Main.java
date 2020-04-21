package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.Decision;
import ooga.BackEnd.GameLogic.LoadGame;
import ooga.view.View;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;


public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException, XMLStreamException {
    new LoadGame("data/boardClassic.xml", 4, primaryStage);
  }
}
