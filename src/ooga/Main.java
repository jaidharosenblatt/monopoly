package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.LoadGame;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;


public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

//  public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
//    LoadGame game = new LoadGame("data/boardClassic.xml", 4);
//  }

  @Override
  public void start(Stage primaryStage) {
    new Controller(primaryStage);
  }
}
