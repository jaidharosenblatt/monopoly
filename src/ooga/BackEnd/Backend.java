package ooga.BackEnd;

import java.util.List;
import ooga.api.BackendExternal;
import ooga.api.view.PlayerInfo;
import ooga.api.view.Property;

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
