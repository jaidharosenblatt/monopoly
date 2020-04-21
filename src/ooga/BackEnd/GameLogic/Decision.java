package ooga.BackEnd.GameLogic;

import java.util.List;

public class Decision implements ooga.api.view.Decision {

  private String prompt;
  private List<String> options;
  private String choice;

  public Decision(String prompt, List<String> options) {
    this.prompt = prompt;
    this.options = options;
  }

  @Override
  public String getPrompt() {
    return prompt;
  }

  @Override
  public List<String> getOptions() {
    return options;
  }

  @Override
  public void setChoice(String choice) {
    this.choice = choice;
  }

  @Override
  public String getChoice() {
    return choice;
  }
}
