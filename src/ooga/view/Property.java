package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ooga.api.view.PlayerInfo;

public class Property extends Tile {

  private static final double BAR_HEIGHT = 20;
  private Color backgroundColor;
  private Color categoryColor;
  private String name;
  private double price;
  private BorderPane horizontalPane;
  private BorderPane verticalPane;


  public Property(String name, double price, Color backgroundColor, Color categoryColor, int width, int height) {
    this.backgroundColor = backgroundColor;
    this.categoryColor = categoryColor;
    this.price = price;
    this.name = name;
    setSize(width,height);

    setHorizontalPane();
    setVerticalPane();
  }


  private void setHorizontalPane(){
    horizontalPane = new BorderPane();
    horizontalPane.setPrefSize(getWidth(), getHeight());
    setBackgroundColor(horizontalPane, backgroundColor);

    VBox box = new VBox();
    box.setAlignment(Pos.CENTER);
    Text nameText = new Text(name);
    nameText.setWrappingWidth(getWidth());

    box.getChildren().addAll(new Rectangle(getWidth(), BAR_HEIGHT, categoryColor), nameText);

    horizontalPane.setTop(box);
    Text text = new Text("$" + price);
    horizontalPane.setBottom(text);
    horizontalPane.setAlignment(text, Pos.CENTER);
  }

  private void setVerticalPane(){
    verticalPane = new BorderPane();
    verticalPane.setPrefSize(getHeight(), getWidth());
    setBackgroundColor(verticalPane, backgroundColor);
    HBox box = new HBox();
    box.setAlignment(Pos.CENTER);
    box.getChildren()
        .addAll(createRotatedText(name, 90), new Rectangle(BAR_HEIGHT, getWidth(), categoryColor));
    verticalPane.setRight(box);

    Text priceText = new Text("M" + price);
    priceText.setRotate(90);
    verticalPane.setLeft(priceText);
    verticalPane.setAlignment(priceText, Pos.CENTER);
  }

  @Override
  public Pane getHorizontalNode() {
    return horizontalPane;
  }

  @Override
  public Pane getVerticalNode() {
    return verticalPane;
  }

  @Override
  public void removePlayer(PlayerInfo player) {

  }

  @Override
  public void addPlayer(PlayerInfo player) {

  }
  

  private Text createRotatedText(String s, double rotate) {
    Text text = new Text(s);
    text.setRotate(rotate);
    text.setWrappingWidth(getWidth());

    return text;
  }
}
