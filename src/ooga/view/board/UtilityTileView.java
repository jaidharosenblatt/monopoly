package ooga.view.board;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * @author jaidharosenblatt represents the utlity tiles on a board (railroads, community chest,
 * chance, etc). Displays an image, a name, and an amount
 */
public class UtilityTileView extends TileView {

  private final double IMAGE_SIZE = 22;

  /**
   * Creates a utility tile
   * @param name     name of the property
   * @param price    price of the property
   * @param filename path in resources to the image to display
   * @param width    width of tile
   * @param height   height of til
   */
  public UtilityTileView(String name, String price, String filename, double width, double height) {
    setPrefSize(width, height);

    ImageView image = new ImageView(getImageByName(filename));
    image.setFitHeight(IMAGE_SIZE);
    image.setFitWidth(IMAGE_SIZE);

    Text nameText = new Text(name);
    setTop(nameText);
    setAlignment(nameText, Pos.CENTER);
    nameText.setWrappingWidth(getPrefWidth());

    setToCenter(image);

    Text priceText = new Text(price);
    setBottom(priceText);
    setAlignment(priceText, Pos.CENTER);
  }
}
