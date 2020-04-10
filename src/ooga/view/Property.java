package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Property extends Tile {

  private static final double BAR_HEIGHT = 20;
  private Color backgroundColor;
  private Color categoryColor;
  private String name;
  private double price;


  public Property(String name, double price, Color backgroundColor, Color categoryColor) {
    this.backgroundColor = backgroundColor;
    this.categoryColor = categoryColor;
    this.price = price;
    this.name = name;
  }

  @Override
  public Pane getHorizontalNode() {
    BorderPane property = new BorderPane();
    property.setPrefSize(getWidth(), getHeight());
    setBackgroundColor(property, backgroundColor);

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);
    Text nameText = new Text(name);
    nameText.setWrappingWidth(getWidth());

    box.getChildren().addAll(new Rectangle(getWidth(), BAR_HEIGHT, categoryColor), nameText);

    property.setTop(box);
    Text text = new Text("$" + price);
    property.setBottom(text);
    property.setAlignment(text, Pos.CENTER);

    return property;
  }

  @Override
  public Pane getVerticalNode() {
    BorderPane property = new BorderPane();
    property.setPrefSize(getHeight(), getWidth());
    setBackgroundColor(property, backgroundColor);
    HBox box = new HBox();
    box.setAlignment(Pos.CENTER);
    box.getChildren()
        .addAll(createRotatedText(name, 90), new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    property.setRight(box);

    Text priceText = new Text("M" + price);
    priceText.setRotate(90);
    property.setLeft(priceText);
    property.setAlignment(priceText, Pos.CENTER);

    return property;
  }

  private Text createRotatedText(String s, double rotate) {
    Text text = new Text(s);
    text.setRotate(rotate);
    text.setWrappingWidth(getWidth());

    return text;
  }
}
