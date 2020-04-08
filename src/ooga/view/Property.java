package ooga.view;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Property extends Tile{

  private static final double WIDTH = 50;
  private static final double BAR_HEIGHT = 20;
  private Color categoryColor;
  private String name;
  private double price;

  public Property(Color backgroundColor, Color categoryColor, String name, double price) {
    super(backgroundColor);
    this.categoryColor = categoryColor;
    this.name = name;
    this.price= price;
    createProperty();
  }

  private void createProperty() {
    setBackgroundColor(Color.BEIGE);

    VBox textBox = new VBox();
    Text nameText = new Text(name);
    Text priceText = new Text("$" + price);
    textBox.getChildren().addAll(nameText, priceText);

    setCenter(textBox);
    Shape category = new Rectangle(WIDTH, BAR_HEIGHT, categoryColor);
    setTop(category);
  }
}
