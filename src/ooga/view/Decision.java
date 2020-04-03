package ooga.view;

import java.util.List;

public interface Decision {

  /**
   * Display a prompt for this decision
   * @return the prompt to display to the player
   */
  String displayPrompt();

  /**
   * Display the choices available to the player
   * @return a list of possible choices as strings
   */
  List<String>  displayChoices();

  /**
   * Set the choice that the player picked in the gui
   * @param choice the choice that the player chose for this decision
   */
  void setChoice(int choice);
}
