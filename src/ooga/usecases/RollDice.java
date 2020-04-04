package ooga.usecases;

import java.util.List;
import ooga.view.Chance;

public class RollDice {
  private List<Integer> rolls;
  private Backend backend = new Backend();
  Chance chance = () -> backend.rollDice();

  private void displayRoll(){
    chance.displayRoll();
  }
}
