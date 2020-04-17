package ooga.BackEnd;

import java.util.List;
import ooga.api.view.Chance;

public class RollDice {
  //change to be flexible with different rules
  private List<Integer> rolls;
  private Backend backend = new Backend();
  Chance chance = () -> backend.rollDice();

  private void displayRoll(){
    chance.displayRoll();
  }
}
