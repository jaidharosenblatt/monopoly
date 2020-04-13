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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import ooga.api.view.PlayerInfo;

public class Property extends Tile {

  private static final double BAR_HEIGHT = 20;
  private static final double PLAYER_SIZE = 10;

  private Color backgroundColor;
  private Color categoryColor;
  private String name;
  private double price;

  private Pane playersPane = new HBox();

  private Map<PlayerInfo, Shape> players = new HashMap<>();


  public Property(String name, double price, Color backgroundColor, Color categoryColor,
      double width, double height) {
    this.backgroundColor = backgroundColor;
    this.categoryColor = categoryColor;
    this.price = price;
    this.name = name;
    setPrefSize(width, height);

    setHorizontalPane();

    setCenter(playersPane);

  }

  private void setHorizontalPane() {
    setBackgroundColor(this, backgroundColor);

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);

    Text nameText = new Text(name);
    nameText.setWrappingWidth(getPrefWidth());

    box.getChildren().addAll(new Rectangle(getPrefWidth(), BAR_HEIGHT, categoryColor), nameText);

    setTop(box);
    Text text = new Text("$" + price);
    setBottom(text);
    setAlignment(text, Pos.CENTER);
  }

  @Override
  public void removePlayer(PlayerInfo player) {
    Shape piece = players.get(player);
    playersPane.getChildren().remove(piece);
    players.remove(player);
  }

  @Override
  public void addPlayer(PlayerInfo player) {
    Shape piece = new Circle(5, Color.web(player.getPlayerColor()));
    players.put(player, piece);
    playersPane.getChildren().clear();
    playersPane.getChildren().addAll(players.values());
  }
}
