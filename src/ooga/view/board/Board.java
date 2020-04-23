package ooga.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.objects.PlayerInfo;


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

  public Board(List<PlayerInfo> players, List<Tile> tiles) {

    for (PlayerInfo p : players) {
      playerPositions.put(p, 0);
    }
    createGrid(tiles);
    setPanesToRoot();

    for (PlayerInfo player : playerPositions.keySet()) {
      //add player to tile 0
      TileView tile = (TileView) bottom.getChildren().get(ROW_LENGTH);
      tile.addPlayer(player);
    }
  }

  public void displayRoll(List<Integer> rolls) {
    HBox hBox = new HBox();
    for (int die : rolls) {
      hBox.getChildren().add(new Die(die));
    }
    setCenter(hBox);
  }

  public void movePlayer(PlayerInfo player, int newPosition) {
    int oldPosition = playerPositions.get(player);
    playerPositions.put(player, newPosition);

    TileView oldTile = getTileByIndex(oldPosition);
    TileView newTile = getTileByIndex(newPosition);

    oldTile.removePlayer(player);
    newTile.addPlayer(player);
  }

  private void createGrid(List<Tile> tiles) {
    TileFactory factory = new TileFactory(TILE_HEIGHT, TILE_WIDTH, ROW_LENGTH);
    for (int i = ROW_LENGTH; i >= 0; i--) {
      bottom.getChildren().add(factory.getPropertyFromTile(tiles.get(i), i));
    }
    for (int i = ROW_LENGTH * 2 - 1; i > ROW_LENGTH; i--) {
      left.getChildren().add(factory.getPropertyFromTile(tiles.get(i), i));
    }
    for (int i = ROW_LENGTH * 2; i <= ROW_LENGTH * 3; i++) {
      top.getChildren().add(factory.getPropertyFromTile(tiles.get(i), i));
    }
    for (int i = ROW_LENGTH * 3 + 1; i < ROW_LENGTH * 4; i++) {
      right.getChildren().add(factory.getPropertyFromTile(tiles.get(i), i));
    }
  }

  private TileView getTileByIndex(int index) {
    index = index % NUMBER_OF_TILES;
    int position = index % ROW_LENGTH;

    if (index < ROW_LENGTH) {
      return (TileView) bottom.getChildren().get(ROW_LENGTH - position);
    } else if (index == ROW_LENGTH) {
      return (TileView) bottom.getChildren().get(0);
    } else if (index < ROW_LENGTH * 2) {
      return (TileView) left.getChildren().get(ROW_LENGTH - position - 1);
    } else if (index < ROW_LENGTH * 3) {
      return (TileView) top.getChildren().get(position);
    } else if (index == ROW_LENGTH * 3) {
      return (TileView) top.getChildren().get(ROW_LENGTH);
    } else {
      return (TileView) right.getChildren().get(position - 1);
    }
  }

  private void setPanesToRoot() {
    setTop(top);
    setRight(right);
    setBottom(bottom);
    setLeft(left);
  }

}
