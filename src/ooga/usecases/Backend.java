package ooga.usecases;

import java.util.List;
import ooga.BackendExternal;
import ooga.PlayerInfo;
import ooga.model.Player;
import ooga.view.Property;

public class Backend implements BackendExternal {


  @Override
  public List<Integer> rollDice() {
    return List.of(0,1);
  }

  @Override
  public List<Property> updateTiles() {
    return null;
  }

  @Override
  public List<PlayerInfo> updatePlaters() {
    return null;
  }

  @Override
  public void buyProperty(Property P) {

  }

  @Override
  public void mortgageProp() {

  }

  @Override
  public void buyHouses(Property P, int amount) {

  }

  @Override
  public void endTurn() {

  }
}
