package ooga.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.api.view.PlayerInfo;

public class Board extends BorderPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = NUMBER_OF_TILES / 4;
  private List<Tile> tiles = new ArrayList<>();
  private List<PlayerInfo> players = new ArrayList<>();
  private HBox top = new HBox();
  private VBox right = new VBox();
  private HBox bottom = new HBox();
  private VBox left = new VBox();


  public Board() {
    for (int i = 0; i < 4; i++) {
      players.add(new PlayerTester(0, i, "2E86AB"));
    }

    createBoard();
    createGrid();

    setPanesToRoot();

    createCenter();
  }

  private void createCenter() {
    Button button = new Button("Take turn");
    button.setOnAction(e -> movePlayer(players.get(0), 3));
    setCenter(button);
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
      top.getChildren().add(tile);
    }

    List<Tile> rightList = tiles.subList(ROW_LENGTH, ROW_LENGTH * 2);
    for (int i = 1; i < rightList.size(); i++) {
      Node tile = rightList.get(i).getVerticalNode();
      tile.setRotate(180);
      right.getChildren().add(tile);
    }

    List<Tile> bottomList = tiles.subList(ROW_LENGTH * 2 + 1, ROW_LENGTH * 3);
    Collections.reverse(bottomList);
    for (int i = 0; i < bottomList.size(); i++) {
      bottom.getChildren().add(bottomList.get(i).getHorizontalNode());
    }

    List<Tile> leftList = tiles.subList(ROW_LENGTH * 3 + 1, ROW_LENGTH * 4);
    Collections.reverse(leftList);
    for (int i = 0; i < leftList.size(); i++) {
      left.getChildren().add(leftList.get(i).getVerticalNode());
    }
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
        Property property = new Property("Some Other Property", i, Color.BISQUE, Color.BLUEVIOLET,
            60, 70);
        property.setSize(60, 70);
        tiles.add(property);
      }
    }
  }

  private void setPanesToRoot() {
    setTop(top);
    setRight(right);
    setBottom(bottom);
    setLeft(left);
  }

}
