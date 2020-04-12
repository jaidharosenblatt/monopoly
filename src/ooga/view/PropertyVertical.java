package ooga.view;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import ooga.api.view.PlayerInfo;

public class PropertyVertical extends Tile {

  private static final double BAR_HEIGHT = 20;
  private static final double PLAYER_SIZE = 10;

  private Color backgroundColor;
  private Color categoryColor;
  private String name;
  private double price;

  private Pane playersPane = new HBox();

  private Map<PlayerInfo, Shape> players = new HashMap<>();


  public PropertyVertical(String name, double price, Color backgroundColor, Color categoryColor) {
    this.setPrefSize(10,30);

    this.backgroundColor = backgroundColor;
    this.categoryColor = categoryColor;
    this.price = price;
    this.name = name;


    setVerticalPane();
  }

  private void setVerticalPane() {
    setBackgroundColor(this, backgroundColor);
    HBox box = new HBox();
    box.setAlignment(Pos.CENTER);
    box.getChildren()
        .addAll(createRotatedText(name, 90), new Rectangle(BAR_HEIGHT, getPrefHeight(), categoryColor));
    setRight(box);

    Text priceText = new Text("M" + price);
    priceText.setRotate(90);
    setLeft(priceText);
    setAlignment(priceText, Pos.CENTER);
  }

  private Text createRotatedText(String s, double rotate) {
    Text text = new Text(s);
    text.setRotate(rotate);
    text.setWrappingWidth(getPrefHeight());

    return text;
  }
}
