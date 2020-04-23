package ooga.view.board;

import javafx.scene.image.ImageView;

/**
 * @author jaidharosenblatt represents the corner tiles on a board (free parking, go to jail, go,
 * jail). Simple image without name or amount
 */
public class CornerTileView extends TileView {

  /**
   * Constructs a corner tile
   * @param filename path in resources to the image to display
   * @param width width of tile
   * @param height height of tile
   */
  public CornerTileView(String filename, double width, double height) {
    setPrefSize(width, height);

    ImageView image = new ImageView(getImageByName(filename));
    image.setFitHeight(getPrefHeight());
    image.setFitWidth(getPrefWidth());
    setToCenter(image);
  }
}
