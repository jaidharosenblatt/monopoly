package ooga.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class Tile {

  private double width;
  private double height;

  public void setSize(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public abstract Pane getVerticalNode();

  public abstract Pane getHorizontalNode();

  protected double getWidth() {
    return width;
  }

  protected double getHeight() {
    return height;
  }

  protected void setBackgroundColor(Pane pane, Color color) {
    pane.setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }


}
