package ooga.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import ooga.api.view.PlayerInfo;

public class Board extends GridPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = NUMBER_OF_TILES / 4;
  private List<Tile> tiles = new ArrayList<>();
  private List<PlayerInfo> players = new ArrayList<>();

  public Board() {
    setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

    for (int i = 0; i < 4; i++) {
      players.add(new PlayerTester(0, i, "2E86AB"));
    }

    createBoard();
    createGrid();
  }

  public void movePlayer(PlayerInfo player, int newPosition) {
    int oldPosition = player.getPositionOnBoard();
    Tile oldTile = tiles.get(oldPosition);
    oldTile.removePlayer(player);
    Tile newTile = tiles.get(newPosition);
    newTile.addPlayer(player);
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

    Button button = new Button("Take turn");
    button.setOnAction(e -> movePlayer(players.get(0), 1));
    add(button, 5, 5);
  }


  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      if (i % 5 == 0 && i % 10 != 0) {
        UtilityTile tile = new UtilityTile("Robert Duvall", i, Color.GREY, "rcd.jpg");
        tiles.add(tile);
      } else if (i % 2 == 0) {
        Property property = new Property("Some Property", i, Color.BISQUE, Color.GREEN, 60, 70);
        property.setSize(60, 70);
        tiles.add(property);
      } else {
        Property property = new Property("Some Other Property", i, Color.BISQUE, Color.BLUEVIOLET, 60, 70);
        property.setSize(60, 70);
        tiles.add(property);
      }
    }
  }

}
