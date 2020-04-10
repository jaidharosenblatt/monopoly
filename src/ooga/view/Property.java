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

public class Property extends Tile {

  private static final double BAR_HEIGHT = 20;
  private static final double PLAYER_SIZE = 10;

  private Color backgroundColor;
  private Color categoryColor;
  private String name;
  private double price;
  private BorderPane horizontalProperty;
  private BorderPane verticalProperty;
  private Pane horizontalPane = new StackPane();
  private Pane verticalPane = new StackPane();

  private Pane playersPane = new HBox();

  private Map<PlayerInfo, Shape> players = new HashMap<>();


  public Property(String name, double price, Color backgroundColor, Color categoryColor, int width,
      int height) {
    this.backgroundColor = backgroundColor;
    this.categoryColor = categoryColor;
    this.price = price;
    this.name = name;
    setSize(width, height);

    setHorizontalPane();
    horizontalPane.getChildren().addAll(horizontalProperty, playersPane);

    setVerticalPane();
    verticalPane.getChildren().addAll(verticalProperty, playersPane);

  }

  @Override
  public void removePlayer(PlayerInfo player) {
    players.remove(player);
    //FIXME
    playersPane.getChildren().remove(players.get(player));

  }

  @Override
  public void addPlayer(PlayerInfo player) {
    System.out.println(playersPane.getChildren().size());

    Shape icon = new Rectangle(PLAYER_SIZE, PLAYER_SIZE, Color.BLACK);
    players.put(player, icon);
    playersPane.getChildren().add(icon);
    System.out.println(playersPane.getChildren().size());

  }

  @Override
  public Pane getHorizontalNode() {
    return horizontalPane;
  }

  @Override
  public Pane getVerticalNode() {
    return verticalPane;
  }

  private void setHorizontalPane() {
    horizontalProperty = new BorderPane();
    horizontalProperty.setPrefSize(getWidth(), getHeight());
    setBackgroundColor(horizontalProperty, backgroundColor);

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);
    Text nameText = new Text(name);
    nameText.setWrappingWidth(getWidth());

    box.getChildren().addAll(new Rectangle(getWidth(), BAR_HEIGHT, categoryColor), nameText);

    horizontalProperty.setTop(box);
    Text text = new Text("$" + price);
    horizontalProperty.setBottom(text);
    horizontalProperty.setAlignment(text, Pos.CENTER);
  }

  private void setVerticalPane() {
    verticalProperty = new BorderPane();
    verticalProperty.setPrefSize(getHeight(), getWidth());
    setBackgroundColor(verticalProperty, backgroundColor);
    HBox box = new HBox();
    box.setAlignment(Pos.CENTER);
    box.getChildren()
        .addAll(createRotatedText(name, 90), new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    verticalProperty.setRight(box);

    Text priceText = new Text("M" + price);
    priceText.setRotate(90);
    verticalProperty.setLeft(priceText);
    verticalProperty.setAlignment(priceText, Pos.CENTER);
  }

  private Text createRotatedText(String s, double rotate) {
    Text text = new Text(s);
    text.setRotate(rotate);
    text.setWrappingWidth(getWidth());

    return text;
  }
}
