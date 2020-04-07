package ooga.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Property extends BorderPane {

  private static final double WIDTH = 50;
  private static final double HEIGHT = 75;
  private static final double BAR_HEIGHT = 20;


  public Property(Color color, String name, double price) {
    setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    Text nameText = new Text(name);
    Text priceText = new Text("$" + price);

    Shape category = new Rectangle(WIDTH, BAR_HEIGHT, color);

    setTop(category);

    setCenter(nameText);
    setBottom(priceText);

    setPrefWidth(WIDTH);
    setPrefHeight(HEIGHT);

  }
}
