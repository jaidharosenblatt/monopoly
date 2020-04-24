package ooga.BackEnd.GameLogic.Decisions;

import java.util.List;

public class Decision implements ooga.api.objects.Decision {

  private String prompt;
  private List<String> options;
  private String choice;

  /**
   * Holds the list of options available to the player and answer once selected
   *
   * @param prompt a string of the message displayed to the player
   * @param options a list of strings containing the possible choices
   */

  public Decision(String prompt, List<String> options) {
    this.prompt = prompt;
    this.options = options;
  }

  /**
   * Display a prompt for this decision
   *
   * @return the prompt to display to the player
   */

  @Override
  public String getPrompt() {
    return prompt;
  }

  /**
   * Display the choices available to the player
   *
   * @return a list of possible choices as strings
   */

  @Override
  public List<String> getOptions() {
    return options;
  }

  /**
   * Set the choice that the player picked in the gui
   *
   */

  @Override
  public void setChoice(String choice) {
    this.choice = choice;
  }

  /**
   * Get the choice that the player picked in the gui
   *
   * @return string
   */

  @Override
  public String getChoice() {
    return choice;
  }
}
