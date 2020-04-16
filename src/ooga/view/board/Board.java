package ooga.view.board;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.api.view.PlayerInfo;
import ooga.view.PlayerTester;

public class Board extends BorderPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = NUMBER_OF_TILES / 4;
  private final static int TILE_WIDTH = 60;
  private final static int TILE_HEIGHT = 60;

  private Map<PlayerInfo, Integer> playerPositions = new HashMap<>();
  private HBox top = new HBox();
  private VBox right = new VBox();
  private HBox bottom = new HBox();
  private VBox left = new VBox();
  int counter = 1;

  public Board() {
    createGrid();
    setPanesToRoot();

    for (int i = 0; i < 4; i++) {
      PlayerInfo player = new PlayerTester(0, i, "2E86AB");
      Tile tile = (Tile) bottom.getChildren().get(ROW_LENGTH);
      tile.addPlayer(player);
      playerPositions.put(player, 0);
    }
    createCenter();
  }

  private void createCenter() {
    Button button = new Button("Take turn");


    //these buttons will need to be injected.
    button.setOnAction(e -> movePlayer(playerPositions.keySet().iterator().next(), counter++));
    setCenter(button);
  }

  public void movePlayer(PlayerInfo player, int newPosition) {

    int oldPosition = playerPositions.get(player);
    playerPositions.put(player, newPosition);

    Tile oldTile = getTileByIndex(oldPosition);
    Tile newTile = getTileByIndex(newPosition);

    oldTile.removePlayer(player);
    newTile.addPlayer(player);
  }

  private void createGrid() {

    for (int i = ROW_LENGTH; i >= 0; i--) {
      Tile tile = new Property("property", i, Color.GREY, Color.BLUEVIOLET, TILE_WIDTH,
          TILE_HEIGHT);
      bottom.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 2 - 1; i > ROW_LENGTH; i--) {
      Tile tile = new Property("property", i, Color.GREY, Color.BLUEVIOLET, TILE_HEIGHT,
          TILE_WIDTH);
      tile.setRotate(90);
      tile.setPrefSize(TILE_HEIGHT, TILE_WIDTH);

      left.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 2; i <= ROW_LENGTH * 3; i++) {
      Tile tile = new UtilityTile("property", i, Color.GREY, "rcd.jpg", TILE_WIDTH,
          TILE_HEIGHT);
      tile.setRotate(180);

      top.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 3 + 1; i < ROW_LENGTH * 4; i++) {
      Tile tile = new Property("property", i, Color.GREY, Color.BLUEVIOLET, TILE_HEIGHT,
          TILE_WIDTH);
      tile.setRotate(270);
      tile.setPrefSize(TILE_HEIGHT, TILE_WIDTH);

      right.getChildren().add(tile);
    }

    bottom.getChildren().remove(ROW_LENGTH);
    bottom.getChildren().add(new CornerTile(Color.GREY, "go.png", TILE_WIDTH, TILE_HEIGHT));

    bottom.getChildren().remove(0);
    bottom.getChildren()
        .add(0, new CornerTile(Color.GREY, "jail.png", TILE_WIDTH, TILE_HEIGHT));

    top.getChildren().remove(ROW_LENGTH);
    top.getChildren().add(new CornerTile(Color.GREY, "gotojail.png", TILE_WIDTH, TILE_HEIGHT));

    top.getChildren().remove(0);
    top.getChildren()
        .add(0, new CornerTile(Color.GREY, "freeparking.png", TILE_WIDTH, TILE_HEIGHT));

  }

  private Tile getTileByIndex(int index) {
    index = index % NUMBER_OF_TILES;
    int position = index % ROW_LENGTH;
    System.out.println(index);

    if (index < ROW_LENGTH) {
      return (Tile) bottom.getChildren().get(ROW_LENGTH - position);
    } else if (index == ROW_LENGTH) {
      return (Tile) bottom.getChildren().get(0);
    } else if (index < ROW_LENGTH * 2) {
      return (Tile) left.getChildren().get(ROW_LENGTH - position - 1);
    } else if (index < ROW_LENGTH * 3) {
      return (Tile) top.getChildren().get(position);
    } else if (index == ROW_LENGTH * 3) {
      return (Tile) top.getChildren().get(ROW_LENGTH);
    } else {
      return (Tile) right.getChildren().get(position - 1);
    }
  }

  private void setPanesToRoot() {
    setTop(top);
    setRight(right);
    setBottom(bottom);
    setLeft(left);
  }

}
