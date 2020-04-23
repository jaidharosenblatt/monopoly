package ooga;

import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.LoadGame;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import ooga.view.splash.SplashScreen;


public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    new SplashScreen(this);
  }

  public void startGame(Map<String, String> playerInfo, String boardType)
      throws FileNotFoundException, XMLStreamException {
    System.out.println(playerInfo);
    new LoadGame(boardType, playerInfo, primaryStage);
  }
}
