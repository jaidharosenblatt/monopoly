package ooga.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Property extends Tile {

  private static final double WIDTH = 50;
  private static final double BAR_HEIGHT = 20;
  private Shape categoryBox;
  private VBox textBox;
  private Color backgroundColor;


  public Property(String name, double price, Color backgroundColor, Color categoryColor) {
    this.backgroundColor = backgroundColor;
    createTextBox(name, price);
    createCategoryBox(categoryColor);
  }

  @Override
  public Pane getVerticalNode() {
    Pane property = new HBox();
    property.setPrefSize(getWidth(), getHeight());
    setBackgroundColor(property, backgroundColor);
    property.getChildren().add(textBox);
    property.getChildren().add(categoryBox);
    return property;
  }

  @Override
  public Pane getHorizontalNode() {
    Pane property = new VBox();
    property.setPrefSize(getHeight(), getWidth());
    textBox.setRotate(90);
    setBackgroundColor(property, backgroundColor);
    property.getChildren().add(textBox);
    property.getChildren().add(categoryBox);
    return property;
  }

  private void createCategoryBox(Color categoryColor) {
    categoryBox = new Rectangle(WIDTH, BAR_HEIGHT, categoryColor);
  }

  private void createTextBox(String name, double price) {
    textBox = new VBox();
    Text nameText = new Text(name);
    Text priceText = new Text("$" + price);
    textBox.getChildren().addAll(nameText, priceText);
  }
}
