package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.XMLParser;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

//  public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
//      XMLParser parse = new XMLParser();
//      ArrayList<Property> properties = parse.propertySetUp("data/boardClassic.xml");
//      for (Property p : properties) {
//          System.out.println(p.getPropID());
//          System.out.println(p.getTitle());
//          System.out.println(p.getBoardIndex());
//          System.out.println(p.getCost());
//          System.out.println(p.getGroupColor());
//          System.out.println(p.getGroupNumber());
//          if (p instanceof Street) {
//              System.out.println("lmao");
//          }
//      }
//      System.out.println();
//      System.out.println(properties.size());
//  }

  @Override
  public void start(Stage primaryStage) {
    new Controller(primaryStage);
  }
}
