package ooga.view.board;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CornerTileView extends TileView {


  public CornerTileView(String filename, double width, double height) {
    setPrefSize(width, height);

    ImageView image = new ImageView(getImageByName(filename));
    image.setFitHeight(getPrefHeight());
    image.setFitWidth(getPrefWidth());
    setToCenter(image);

  }
}
