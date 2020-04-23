package ooga.view.board;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PropertyView extends TileView {

  private static final double BAR_HEIGHT = 10;

  public PropertyView(String name, String price, Color categoryColor,
      double width, double height) {
    //remove bg colors

    setPrefSize(width, height);

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);

    Text nameText = new Text(name);
    nameText.setWrappingWidth(getPrefWidth());

    box.getChildren().addAll(new Rectangle(getPrefWidth(), BAR_HEIGHT, categoryColor), nameText);
    setTop(box);

    Text text = new Text(price);
    setBottom(text);
    setAlignment(text, Pos.CENTER);
  }
}
