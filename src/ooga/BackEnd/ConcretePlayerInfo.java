package ooga.BackEnd;

import java.util.Collections;
import java.util.List;
import ooga.api.view.PlayerInfo;
import ooga.api.view.Card;
import ooga.api.view.Property;

public class ConcretePlayerInfo implements PlayerInfo {

  private List<Property> myProperties = null;
  private List<Card> myCards = null;
  private Integer cashBalance = null;
  private Integer boardPosition = null;
  private int playerId;

  public ConcretePlayerInfo(List<Property> properties, List<Card> cards, int money, int position, int id){
    myProperties = properties;
    myCards = cards;
    cashBalance = money;
    boardPosition = position;
    playerId = id;
  }

  public ConcretePlayerInfo(int id){
    playerId = id;
  }

  /**
   * set method for cash balance that should only be called if a players balance has changed. Otherwise
   * the cash balance will be set to null and so the view will know not to update it.
   * @param balance the current balance for a player
   */
  public void changeCashBalance(int balance){
    cashBalance = balance;
  }

  public void changeProperties(List<Property> properties){
    myProperties = properties;
  }

  public void changeCards(List<Card> cards){
    myCards = cards;
  }

  public void changePosition(int position){
    boardPosition = position;
  }
  @Override
  public List<Property> getPropertiesUnmodifiable() {
    return Collections.unmodifiableList(myProperties);
  }

  @Override
  public List<Card> getHeldCardsUnmodifiable() {
    return Collections.unmodifiableList(myCards);
  }

  @Override
  public Integer getCashBalance() {
    return cashBalance;
  }

  @Override
  public Integer getPositionOnBoard() {
    return boardPosition;
  }

  @Override
  public int getPlayerId() {
    return playerId;
  }

  @Override
  public String getPlayerColor() {
    return null;
  }
}
