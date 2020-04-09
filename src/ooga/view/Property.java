package ooga.view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Property extends Tile {

  private static final double BAR_HEIGHT = 20;
  private VBox textBox;
  private Color backgroundColor;
  private Color categoryColor;


  public Property(String name, double price, Color backgroundColor, Color categoryColor) {
    this.backgroundColor = backgroundColor;
    this.categoryColor = categoryColor;

    createTextBox(name, price);
  }

  @Override
  public Pane getHorizontalNode() {
    Pane property = new VBox();
    property.setPrefSize(getWidth(), getHeight());
    setBackgroundColor(property, backgroundColor);

    property.getChildren().add(new Rectangle(getWidth(), BAR_HEIGHT, categoryColor));
    property.getChildren().add(textBox);
    return property;
  }

  @Override
  public Pane getLeftNode() {
    Pane property = getVerticalNode(90);
    property.getChildren().add(textBox);
    property.getChildren().add(new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    return property;
  }

  @Override
  public Pane getRightNode() {
    Pane property = getVerticalNode(270);
    property.getChildren().add(new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    property.getChildren().add(textBox);
    return property;
  }

  private Pane getVerticalNode(double rotate) {
    Pane property = new HBox();
    property.setPrefSize(getHeight(), getWidth());
    setBackgroundColor(property, backgroundColor);
    textBox.setRotate(rotate);
    return property;
  }

  private void createTextBox(String name, double price) {
    textBox = new VBox();
    Text nameText = new Text(name);
    Text priceText = new Text("$" + price);
    textBox.getChildren().addAll(nameText, priceText);
  }
}
