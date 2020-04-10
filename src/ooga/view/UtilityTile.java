package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class UtilityTile extends Tile {

  private Color backgroundColor;
  private String name;
  private double price;
  private ImageView image;

  public UtilityTile(String name, double price, Color backgroundColor, String filename) {
    this.backgroundColor = backgroundColor;
    this.price = price;
    this.name = name;
    image = new ImageView(getImageByName(filename));
    image.setFitHeight(20);
    image.setFitWidth(20);

  }

  @Override
  public Pane getHorizontalNode() {
    BorderPane tile = new BorderPane();
    setBackgroundColor(tile, backgroundColor);
    tile.setCenter(image);
    return tile;
  }

  @Override
  public Pane getVerticalNode() {
    BorderPane tile = new BorderPane();
    setBackgroundColor(tile, backgroundColor);
    tile.setCenter(image);
    return tile;
  }


}
