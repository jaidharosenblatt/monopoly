package ooga.api.objects;

import java.util.List;

public interface Decision {

  /**
   * Display a prompt for this decision
   *
   * @return the prompt to display to the player
   */
  String getPrompt();

  /**
   * Display the choices available to the player
   *
   * @return a list of possible choices as strings
   */
  List<String> getOptions();

  /**
   * Set the choice that the player picked in the gui
   *
   */
  void setChoice(String choice);

  /**
   * Get the choice that the player picked in the gui
   *
   * @return string
   */

  String getChoice();
}
