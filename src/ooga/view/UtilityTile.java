package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


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
    image.setFitHeight(30);
    image.setFitWidth(30);

  }

  @Override
  public Pane getHorizontalNode() {
    BorderPane tile = new BorderPane();
    setBackgroundColor(tile, backgroundColor);

    Text nameText = new Text(name);
    tile.setTop(nameText);
    tile.setAlignment(nameText, Pos.CENTER);

    tile.setCenter(image);
    tile.setPrefSize(getWidth(), getHeight());

    Text priceText = new Text("M" + price);
    tile.setBottom(priceText);
    tile.setAlignment(priceText, Pos.CENTER);

    return tile;
  }

  @Override
  public Pane getVerticalNode() {
    Pane pane = getHorizontalNode();
    return pane;
  }


}
