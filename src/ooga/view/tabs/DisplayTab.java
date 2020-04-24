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

public abstract class DisplayTab {
  private static final double FONT_SIZE = 12;
  private static final double SPACING = 10;
  Tab myTab;
  Pane myPane;
  public DisplayTab(String tabName, Pane pane){
    myTab = new Tab(tabName, pane);
    myPane = pane;
  }

  abstract void updateTab(Collection<Object> info);

  protected Tab getTab(){return myTab;}

  protected void addTitle(String str) {
    HBox hbox = new HBox();
    Text title = new Text(str);
    title.setFont(new Font(FONT_SIZE*2));
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().add(title);
    myPane.getChildren().add(hbox);
  }

  HBox makeColorAndTextHBox(String color, String text, boolean property, Property p) {
    Circle circle = new Circle(FONT_SIZE /2, Color.web(color));
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
