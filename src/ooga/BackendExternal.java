package ooga;

import java.util.List;
import ooga.model.Player;
import ooga.view.Property;

public interface BackendExternal {
  List<Integer> rollDice();

  List<Property> updateTiles();

  List<PlayerInfo> updatePlaters();

  void buyProperty(Property P);

  void mortgageProp();

  void buyHouses(Property P, int amount);

  void endTurn();
}
