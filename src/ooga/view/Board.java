package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Board extends BorderPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = 9;
  private List<Property> tiles = new ArrayList<>();

  public Board() {
    setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

    createBoard();

    //Spent an hour trying to reduce duplicate code here and still left with this :(
    setTop(createTop());
    setBottom(createBottom());
    setRight(createRight());
    setLeft(createLeft());

  }

  private HBox createTop() {
    HBox hbox = new HBox();
    for (int i = 0; i < ROW_LENGTH; i++) {
      hbox.getChildren().add(tiles.get(i));
    }
    return hbox;
  }

  private VBox createRight() {
    VBox vbox = new VBox();
    for (int i = ROW_LENGTH; i < ROW_LENGTH * 2; i++) {
      vbox.getChildren().add(tiles.get(i));
    }
    return vbox;
  }

  private HBox createBottom() {
    HBox hbox = new HBox();
    for (int i = ROW_LENGTH * 3; i >= ROW_LENGTH * 2; i--) {
      hbox.getChildren().add(tiles.get(i));
    }
    return hbox;
  }

  private VBox createLeft() {
    VBox vbox = new VBox();
    for (int i = ROW_LENGTH * 4; i >= ROW_LENGTH * 3; i--) {
      vbox.getChildren().add(tiles.get(i));
    }
    return vbox;
  }

  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = new Property(Color.BLACK, "hello", i);
      tiles.add(property);
    }
  }

}
