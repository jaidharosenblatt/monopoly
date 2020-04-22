package ooga.api;

import java.util.List;
import ooga.api.objects.PlayerInfo;
import ooga.api.objects.Property;

public interface BackendExternal {
  List<Integer> rollDice();

  List<Property> updateTiles();

  List<PlayerInfo> updatePlaters();

  void buyProperty(Property P);

  void mortgageProp();

  void buyHouses(Property P, int amount);

  void endTurn();
}
