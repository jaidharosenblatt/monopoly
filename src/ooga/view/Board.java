package ooga.view;

import java.util.ArrayList;
import java.util.List;
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

    createGrid();

    setPanesToRoot();

    createCenter();
  }

  private void createCenter() {
    Button button = new Button("Take turn");
    button.setOnAction(e -> movePlayer(players.get(0), 20));
    setCenter(button);
  }

  public void movePlayer(PlayerInfo player, int newPosition) {
    int oldPosition = player.getPositionOnBoard();
    Tile oldTile = getTileByIndex(newPosition);
    oldTile.setBackgroundColor(oldTile,Color.GREEN);
  }

  private void createGrid() {

    for (int i = 0; i < ROW_LENGTH; i++) {
      Tile tile = new PropertyHorizontal("property", i, Color.GREY, Color.BLUEVIOLET);
      tile.setRotate(180);
      top.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH; i < ROW_LENGTH * 2; i++) {
      Tile tile = new PropertyVertical("property", i, Color.GREY, Color.BLUEVIOLET);
      tile.setRotate(180);
      right.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 3; i > ROW_LENGTH * 2; i--) {
      Tile tile = new PropertyHorizontal("property", i, Color.GREY, Color.BLUEVIOLET);
      bottom.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 4; i > ROW_LENGTH * 3; i--) {
      Tile tile = new PropertyVertical("property", i, Color.GREY, Color.BLUEVIOLET);
      left.getChildren().add(tile);
    }
  }

  private Tile getTileByIndex(int index) {
    int position = index % ROW_LENGTH;

    if (index < ROW_LENGTH) {
      return (Tile) top.getChildren().get(position);
    }
    else if (index < ROW_LENGTH * 2) {
      return (Tile) right.getChildren().get(position);
    }
    else if (index < ROW_LENGTH * 3) {
      return (Tile) bottom.getChildren().get(position);
    } else {
      return (Tile) left.getChildren().get(position);
    }
  }

  private void setPanesToRoot() {
    setTop(top);
    setRight(right);
    setBottom(bottom);
    setLeft(left);
  }

}
