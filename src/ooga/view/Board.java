package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = 10;
  private List<Property> tiles = new ArrayList<>();

  public Board() {
    setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

    createBoard();
    createGrid();
  }


  private void createGrid() {
    for (int i = 0; i < ROW_LENGTH; i++) {
      add(tiles.get(i), i, 0);
    }
    for (int i = 0; i < ROW_LENGTH; i++) {
      add(tiles.get(i + ROW_LENGTH), ROW_LENGTH, i);
    }
    for (int i = 0; i < ROW_LENGTH; i++) {
      add(tiles.get(ROW_LENGTH * 3 - i - 1), i, ROW_LENGTH);
    }

    for (int i = 0; i < ROW_LENGTH; i++) {
      add(tiles.get(ROW_LENGTH * 4 - i - 1), 0, i);
    }

  }


  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = new Property(Color.BLACK, "hello", i);
      tiles.add(property);
    }
  }

}
