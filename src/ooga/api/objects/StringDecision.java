package ooga.api.objects;

public interface StringDecision {

  /**
   * Display a prompt for this decision
   */
  String getPrompt();

  /**
   * Input that the player entered in the gui
   */
  void setChoice(String input);

  /**
   * Get input
   * @return the input from user
   */
  String getChoice();
}
