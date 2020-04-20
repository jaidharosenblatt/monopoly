package ooga.view.board;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class UtilityTileView extends TileView {

  ImageView image;

  public UtilityTileView(String name, double price, String filename, double width, double height) {
    setPrefSize(width, height);

    image = new ImageView(getImageByName(filename));
    image.setFitHeight(30);
    image.setFitWidth(30);

    Text nameText = new Text(name);
    setTop(nameText);
    setAlignment(nameText, Pos.CENTER);
    nameText.setWrappingWidth(getPrefWidth());

    setToCenter(image);

    Text priceText = new Text("M" + price);
    setBottom(priceText);
    setAlignment(priceText, Pos.CENTER);
  }
}
