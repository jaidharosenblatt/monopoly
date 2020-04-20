package ooga.view;

import java.util.List;
import ooga.api.view.Card;
import ooga.api.view.PlayerInfo;
import ooga.view.board.PropertyView;

public class PlayerTester implements PlayerInfo {

  private int position;
  private int playerId;
  private String playerColor;

  public PlayerTester(int position, int playerId, String playerColor) {
    this.position = position;
    this.playerId = playerId;
    this.playerColor = playerColor;
  }

  @Override
  public List<PropertyView> getPropertiesUnmodifiable() {
    return null;
  }

  @Override
  public List<Card> getHeldCardsUnmodifiable() {
    return null;
  }

  @Override
  public Integer getCashBalance() {
    return null;
  }

  @Override
  public Integer getPositionOnBoard() {
    return position;
  }

  @Override
  public int getPlayerId() {
    return playerId;
  }

  @Override
  public String getPlayerColor() {
    return playerColor;
  }
}
