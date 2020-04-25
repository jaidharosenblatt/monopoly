package ooga.view.tabs;

import java.util.Collection;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;

/**
 * @author Austin
 * Parent class for the different tabs. This class holds the JavaFX tab class that is necessary for using the TabPlane.
 * This class provides the methods updateTab, addTitle, and makeColorandTextHbox. Update tab is an abstract method that
 * is used for each of the different types of tabs to update. AddTitle is a general use method that all tabs used to display
 * their title at the top of the tab. The final method is used to create a horizontally aligned circle of color and text.
 */
public abstract class DisplayTab {
  private static final double FONT_SIZE = 12;
  private static final double SPACING = 10;
  Tab myTab;
  Pane myPane;
  public DisplayTab(String tabName, Pane pane){
    myTab = new Tab(tabName, pane);
    myPane = pane;
  }

  /**
   * This method is the gateway to updating the information displayed in the tabs. It is very generic in its parameters and
   * thus assumes the right thing is being passed into the corresponding tab. For example the rules tab assumes
   * all of the objects in the collection are String.
   * @param info The updated information that needs to be displayed in the respective tab.
   */
  abstract void updateTab(Collection<Object> info);

  /**
   * Provides access to the underlying jfx tab.
   * @return the JFX tab that composes this class.
   */
  protected Tab getTab(){return myTab;}

  /**
   * Creates a Text object for the title for a tab.
   * @param str the text that is the title for the tab
   */
  protected void addTitle(String str) {
    HBox hbox = new HBox();
    Text title = new Text(str);
    title.setFont(new Font(FONT_SIZE*2));
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().add(title);
    myPane.getChildren().add(hbox);
  }

  /**
   * Creates a horizontally laid out circle of the given color and text to display
   * @param color a String representing the web format of a color in hex
   * @param text The text to be displayed
   * @param property is this a property
   * @param p the property to be displayed
   * @return
   */
  HBox makeColorAndTextHBox(String color, String text, boolean property, Property p) {
    Color color1 =  Color.web(color);
    if (property && p.isMortgaged()) {
      color1 = Color.web(color, 0.25);
    }
    Circle circle = new Circle(FONT_SIZE /2, color1);
    Text playerNum = new Text(text);
    HBox hBox = new HBox(SPACING);
    hBox.getChildren().addAll(circle,playerNum);
    if (property && p instanceof Street) {
      HBox houses = new HBox(SPACING / 2);
      for (int i = 0; i < ((Street) p).getHouses(); i++) {
        ImageView house = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("house.png")));
        house.setFitHeight(SPACING);
        house.setFitWidth(SPACING);
        houses.getChildren().add(house);
      }
      hBox.getChildren().add(houses);
    }
    return hBox;
  }

}
