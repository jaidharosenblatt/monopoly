package ooga.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Property extends BorderPane{

  private static final double WIDTH = 50;
  private static final double HEIGHT = 75;
  private static final double BAR_HEIGHT = 20;
  private Color color;
  private String name;
  private double price;

  public Property(Color color, String name, double price) {
    this.color = color;
    this.name = name;
    this.price= price;
    createProperty();
  }

  private void createProperty() {
    setBackground(
        new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    VBox textBox = new VBox();
    Text nameText = new Text(name);
    Text priceText = new Text("$" + price);
    textBox.getChildren().addAll(nameText, priceText);

    setCenter(textBox);
    Shape category = new Rectangle(WIDTH, BAR_HEIGHT, color);
    setTop(category);
  }
}
