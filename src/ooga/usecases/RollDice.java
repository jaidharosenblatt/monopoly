package ooga.usecases;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import ooga.FrontEndExternal;
import ooga.view.Chance;
import ooga.view.Decision;
import ooga.view.PlayerInfo;

public class RollDice {
  FrontEndExternal api = new FrontEndExternal() {
    @Override
    public List<Integer> makeUserDecision(Decision decision) {
      return null;
    }

    @Override
    public void displayText(String text) {

    }

    @Override
    public void refreshPlayers(Map<Integer, PlayerInfo> currentPlayers) {

    }

    @Override
    public void movePlayer(int playerId, int numSpaces) {

    }

    @Override
    public List<Integer> getRoll() {
      return null;
    }

    @Override
    public void changeTheme(String pathToThemePropertyFile) throws FileNotFoundException {

    }
  };

  Chance chance = () -> api.getRoll();

}
