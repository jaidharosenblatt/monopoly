package ooga.view;

import java.util.ArrayList;
import java.util.Collections;
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

    List<Tile> topList = tiles.subList(0, ROW_LENGTH);
    for (int i = 1; i < topList.size(); i++) {
      Node tile = topList.get(i).getHorizontalNode();
      tile.setRotate(180);
      add(tile, i, 0);
    }

    List<Tile> rightList = tiles.subList(ROW_LENGTH, ROW_LENGTH * 2);
    for (int i = 1; i < rightList.size(); i++) {
      Node tile = rightList.get(i).getVerticalNode();
      tile.setRotate(180);
      add(tile, ROW_LENGTH, i % ROW_LENGTH);
    }

    List<Tile> bottomList = tiles.subList(ROW_LENGTH * 2 + 1, ROW_LENGTH * 3);
    Collections.reverse(bottomList);
    for (int i = 0; i < bottomList.size(); i++) {
      add(bottomList.get(i).getHorizontalNode(), i + 1, ROW_LENGTH);
    }

    List<Tile> leftList = tiles.subList(ROW_LENGTH * 3 + 1, ROW_LENGTH * 4);
    Collections.reverse(leftList);
    for (int i = 0; i < leftList.size(); i++) {
      add(leftList.get(i).getVerticalNode(), 0, i % ROW_LENGTH + 1);
    }
  }


  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      if (i % 5 == 0 && i % 10 != 0) {
        UtilityTile tile = new UtilityTile("hi", i, Color.GREEN, "rcd.jpg");
        tile.setSize(60, 80);
        tiles.add(tile);
      } else {
        Property property = new Property("hi", i, Color.BISQUE, Color.BLACK);
        property.setSize(60, 80);
        tiles.add(property);
      }
    }
  }

}
