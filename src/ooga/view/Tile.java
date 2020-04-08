package ooga.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Tile extends BorderPane {

  public Tile(Color color){
    setBackgroundColor(color);
  }

  protected void setBackgroundColor(Color color) {
    setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }
}
