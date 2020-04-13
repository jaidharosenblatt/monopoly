package ooga.view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ooga.api.view.PlayerInfo;

public abstract class Tile extends BorderPane {
  public abstract void removePlayer(PlayerInfo player);

  public abstract void addPlayer(PlayerInfo player);

  protected void setBackgroundColor(Pane pane, Color color) {
    pane.setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  protected Image getImageByName(String name) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

}
