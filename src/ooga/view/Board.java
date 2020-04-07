package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
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

    setTop(createHorizontalTileRow(0, ROW_LENGTH));
    setBottom(createHorizontalTileRow(ROW_LENGTH * 2, ROW_LENGTH * 3));
    setLeft(createSideTileRow(ROW_LENGTH, ROW_LENGTH * 2));
    setRight(createSideTileRow(ROW_LENGTH*3, ROW_LENGTH * 4));

  }

  private HBox createHorizontalTileRow(int start, int end) {
    HBox hbox = new HBox();
    for (int i = start; i < end; i++) {
      hbox.getChildren().add(tiles.get(i));
    }
    return hbox;
  }

  private VBox createSideTileRow(int start, int end) {
    VBox vbox = new VBox();
    for (int i = start; i < end; i++) {
      Property tile = tiles.get(i);
      tiles.get(i).setRotate(90);
      vbox.getChildren().add(tile);
    }
    return vbox;
  }

  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = new Property(Color.BLACK, "hello", 500);
      tiles.add(property);
    }
  }

}
