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
    //top
    for (int col = 0; col < ROW_LENGTH; col++) {
      Property property = tiles.get(col);
      property.setRotate(180);
      add(property, col, 0);
    }
    //right
    for (int row = 0; row <= ROW_LENGTH; row++) {
      Property property = tiles.get(row + ROW_LENGTH);
      property.setRotate(270);
      add(property, ROW_LENGTH, row);
    }
    //bottom
    for (int col = 0; col < ROW_LENGTH; col++) {
      Property property = tiles.get(ROW_LENGTH * 3 - col);
      add(property, col, ROW_LENGTH);
    }
    //left
    for (int row = 0; row < ROW_LENGTH - 1; row++) {
      Property property = tiles.get(ROW_LENGTH * 4 - row - 1);
      property.setRotate(90);
      add(property, 0, row + 1);
    }
  }


  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = new Property(Color.BLACK, "hi", i);
      tiles.add(property);
    }
  }

}
