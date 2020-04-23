package ooga.view.board;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @author jaidharosenblatt represents the property tiles on a board. Displays a text amount, the
 * category with a color, and its price
 */
public class PropertyView extends TileView {

  private static final double BAR_HEIGHT = 10;

  /**
   * Constructs a new property
   *
   * @param name          name of the property
   * @param price         price of the property
   * @param categoryColor color to display on category bar
   * @param width         width of tile
   * @param height        height of til
   */
  public PropertyView(String name, String price, Color categoryColor, double width, double height) {
    setPrefSize(width, height);
    VBox box = new VBox();

    Text nameText = new Text(name);
    nameText.setWrappingWidth(getPrefWidth());

    box.getChildren().addAll(new Rectangle(getPrefWidth(), BAR_HEIGHT, categoryColor), nameText);
    setTop(box);

    Text text = new Text(price);
    setBottom(text);
    setAlignment(text, Pos.CENTER);
  }
}
