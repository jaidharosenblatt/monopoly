package ooga.view.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;


public class Board extends BorderPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = NUMBER_OF_TILES / 4;
  private final static int TILE_WIDTH = 60;
  private final static int TILE_HEIGHT = 60;

  private Map<Player, Integer> playerPositions = new HashMap<>();
  private HBox top = new HBox();
  private VBox right = new VBox();
  private HBox bottom = new HBox();
  private VBox left = new VBox();

  public Board(List<Player> players, List<Tile> tiles) {
    Collections.sort(tiles, Comparator.comparingInt(Tile::getBoardIndex));

    for (Player p : players) {
      playerPositions.put(p, 0);
    }
    createGrid(tiles);
    setPanesToRoot();

    for (Player player : playerPositions.keySet()) {
      //add player to tile 0
      TileView tile = (TileView) bottom.getChildren().get(ROW_LENGTH);
      tile.addPlayer(player);
    }
  }

  public void displayRoll(List<Integer> rolls) {
    HBox hBox = new HBox();
    hBox.setAlignment(Pos.CENTER);
    for (int die : rolls) {
      hBox.getChildren().add(new Die(die));
    }
    setCenter(hBox);
  }

  public void movePlayer(Player player, int newPosition) {

    int oldPosition = playerPositions.get(player);
    playerPositions.put(player, newPosition);

    TileView oldTile = getTileByIndex(oldPosition);
    TileView newTile = getTileByIndex(newPosition);

    oldTile.removePlayer(player);
    newTile.addPlayer(player);
  }

  private void createGrid(List<Tile> tiles) {

    for (int i = ROW_LENGTH; i >= 0; i--) {

      TileView tile = getPropertyFromTile(tiles.get(i));
      bottom.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 2 - 1; i > ROW_LENGTH; i--) {
      TileView tile = getPropertyFromTile(tiles.get(i));
      tile.setRotate(90);
      tile.setPrefSize(TILE_HEIGHT, TILE_WIDTH);

      left.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 2; i <= ROW_LENGTH * 3; i++) {
      TileView tile = getPropertyFromTile(tiles.get(i));
      tile.setRotate(180);

      top.getChildren().add(tile);
    }

    for (int i = ROW_LENGTH * 3 + 1; i < ROW_LENGTH * 4; i++) {
      TileView tile = getPropertyFromTile(tiles.get(i));
      tile.setRotate(270);
      tile.setPrefSize(TILE_HEIGHT, TILE_WIDTH);

      right.getChildren().add(tile);
    }

    bottom.getChildren().remove(ROW_LENGTH);
    bottom.getChildren().add(new CornerTileView( "go.png", TILE_WIDTH, TILE_HEIGHT));

    bottom.getChildren().remove(0);
    bottom.getChildren()
        .add(0, new CornerTileView("jail.png", TILE_WIDTH, TILE_HEIGHT));

    top.getChildren().remove(ROW_LENGTH);
    top.getChildren().add(new CornerTileView("gotojail.png", TILE_WIDTH, TILE_HEIGHT));

    top.getChildren().remove(0);
    top.getChildren()
        .add(0, new CornerTileView("freeparking.png", TILE_WIDTH, TILE_HEIGHT));

  }

  private TileView getPropertyFromTile(Tile t) {
    if (t instanceof Property) {
      Property p = (Property) t;
      return p.convertFront();
    } else {
      return new UtilityTileView("property", 30, "rcd.jpg", TILE_WIDTH,
          TILE_HEIGHT);
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
