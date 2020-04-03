package view;

import java.util.List;

public interface Chance {

  /**
   * Display a current roll
   * @return roll a list of numbers determined from game engine
   */
  List<Integer> displayRoll();
}