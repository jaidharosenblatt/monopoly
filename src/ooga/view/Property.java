package ooga.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    BorderPane property = new BorderPane();
    property.setPrefSize(getWidth(), getHeight());
    setBackgroundColor(property, backgroundColor);

    property.setTop(new Rectangle(getWidth(), BAR_HEIGHT, categoryColor));
    property.setCenter(textBox);
    return property;
  }

  @Override
  public Pane getLeftNode() {
    BorderPane property = getVerticalNode(90);
    property.setCenter(textBox);
    property.setRight(new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    return property;
  }

  @Override
  public Pane getRightNode() {
    BorderPane property = getVerticalNode(270);
    property.setCenter(textBox);
    property.setLeft(new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    return property;
  }

  private BorderPane getVerticalNode(double rotate) {
    BorderPane property = new BorderPane();
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
