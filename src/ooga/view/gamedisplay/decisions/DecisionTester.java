package ooga.view.gamedisplay.decisions;

import java.util.List;
import ooga.api.view.Decision;

public class DecisionTester implements Decision {

  private String prompt;
  private List<String> choices;

  public DecisionTester(String prompt, List<String> choices) {
    this.prompt = prompt;
    this.choices = choices;
  }

  @Override
  public String getPrompt() {
    return prompt;
  }

  @Override
  public List<String> getOptions() {
    return choices;
  }

  @Override
  public List<Integer> getChoices() {
    return null;
  }

}
