package ooga.api;

import java.util.List;
import ooga.api.view.PlayerInfo;
import ooga.api.view.Property;

public interface BackendExternal {
  List<Integer> rollDice();

  List<Property> updateTiles();

  List<PlayerInfo> updatePlaters();

  void buyProperty(Property P);

  void mortgageProp();

  void buyHouses(Property P, int amount);

  void endTurn();
}
