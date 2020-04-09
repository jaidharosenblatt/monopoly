package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = 10;
  private List<Tile> tiles = new ArrayList<>();

  public Board() {
    setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

    createBoard();
    createGrid();
  }

  private void createGrid() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Node tile = tiles.get(i).getHorizontalNode();
      if (i % ROW_LENGTH == 0) {
        continue;
      }
      //top
      if (i < ROW_LENGTH) {
        tile.setRotate(180);
        add(tile, i, 0);
      }

      //right
      if (i >= ROW_LENGTH && i < ROW_LENGTH * 2) {
        tile = tiles.get(i).getRightNode();
        add(tile, ROW_LENGTH, i % ROW_LENGTH);
      }

      //bottom
      if (i >= ROW_LENGTH * 2 && i < ROW_LENGTH * 3) {
        int backwardsIndex = ROW_LENGTH * 3 - i % ROW_LENGTH;
        tile = tiles.get(backwardsIndex).getHorizontalNode();
        add(tile, i - 2 * ROW_LENGTH, ROW_LENGTH);
      }

      //left
      if (i >= ROW_LENGTH * 3) {
        int backwardsIndex = ROW_LENGTH * 4 - i % ROW_LENGTH;
        tile = tiles.get(backwardsIndex).getLeftNode();
        add(tile, 0, i % ROW_LENGTH);
      }
    }
  }


  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = new Property("hi", i, Color.BISQUE, Color.BLACK);
      property.setSize(50, 40);
      tiles.add(property);
    }
  }

}
