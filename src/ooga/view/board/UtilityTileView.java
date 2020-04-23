package ooga.view.board;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class UtilityTileView extends TileView {

  ImageView image;
  private final double IMAGE_SIZE = 22;

  public UtilityTileView(String name, String price, String filename, double width, double height) {
    setPrefSize(width, height);

    image = new ImageView(getImageByName(filename));
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
