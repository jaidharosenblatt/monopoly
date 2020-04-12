package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class UtilityTileVertical extends Tile {


  public UtilityTileVertical(String name, double price, Color backgroundColor, String filename,
      double width, double height) {
    setPrefSize(width, height);

    ImageView image = new ImageView(getImageByName(filename));
    image.setFitHeight(30);
    image.setFitWidth(30);

    setBackgroundColor(this, backgroundColor);

    Text nameText = new Text(name);
    nameText.setRotate(90);
    nameText.setWrappingWidth(getPrefWidth());

    setRight(nameText);

    image.setRotate(90);
    setCenter(image);
    setPrefSize(getPrefWidth(), getPrefHeight());

    Text priceText = new Text("M" + price);
    priceText.setRotate(90);
    setLeft(priceText);
    setAlignment(priceText, Pos.CENTER);

  }


}
