package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.api.view.PlayerInfo;


public class UtilityTile extends Tile {

  public UtilityTile(String name, double price, Color backgroundColor, String filename) {
    setPrefSize(60, 60);
    setBackgroundColor(this, backgroundColor);

    ImageView image = new ImageView(getImageByName(filename));
    image.setFitHeight(30);
    image.setFitWidth(30);

    Text nameText = new Text(name);
    setTop(nameText);
    setAlignment(nameText, Pos.CENTER);
    nameText.setWrappingWidth(getPrefWidth());

    setCenter(image);

    Text priceText = new Text("M" + price);
    setBottom(priceText);
    setAlignment(priceText, Pos.CENTER);

  }

  @Override
  public void removePlayer(PlayerInfo player) {

  }

  @Override
  public void addPlayer(PlayerInfo player) {

  }
}
