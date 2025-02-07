package ooga.view.board;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import ooga.api.objects.PlayerInfo;

/**
 * @author jaidharosenblatt Tile object representing a space on a board. Is able to hold players
 * icons
 */
public abstract class TileView extends BorderPane {

  private static final double PLAYER_SIZE = 5;
  private HBox playersPane = new HBox();
  private Map<PlayerInfo, Shape> players = new HashMap<>();
  private StackPane centerTile = new StackPane();

  protected TileView() {
    this.setId("tile");
    centerTile.getChildren().add(playersPane);
    setCenter(centerTile);
  }

  protected void setToCenter(Node node) {
    centerTile.getChildren().clear();
    centerTile.getChildren().add(node);
    centerTile.getChildren().add(playersPane);
  }

  protected Image getImageByName(String name) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

  public void removePlayer(PlayerInfo player) {
    Shape piece = players.get(player);
    playersPane.getChildren().remove(piece);
    players.remove(player);
  }

  public void addPlayer(PlayerInfo player) {
    Shape piece = new Circle(PLAYER_SIZE, Color.web(player.getPlayerColor()));
    players.put(player, piece);
    playersPane.getChildren().clear();
    playersPane.getChildren().addAll(players.values());
  }

}
