package ooga.api.objects;

import java.util.List;

public interface Board {
  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param player player to move
   * @param newPosition position on board to move to
   */
  void movePlayer(PlayerInfo player, int newPosition);

  /**
   * Use this to display the result of the dice roll
   *
   * @return a list of the value each die had
   */
  void displayRoll(List<Integer> rolls);
}
