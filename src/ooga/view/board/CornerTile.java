package ooga.view.board;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CornerTile extends Tile {


  public CornerTile(Color backgroundColor, String filename, double width, double height) {
    setPrefSize(width, height);
    setBackgroundColor(this, backgroundColor);

    ImageView image = new ImageView(getImageByName(filename));
    image.setFitHeight(getPrefHeight());
    image.setFitWidth(getPrefWidth());
    setToCenter(image);

  }
}
