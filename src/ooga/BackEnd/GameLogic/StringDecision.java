package ooga.BackEnd.GameLogic;

public class StringDecision implements ooga.api.objects.StringDecision {
  private String prompt;
  private String choice;

  public StringDecision(String prompt) {
    this.prompt = prompt;
  }

  @Override
  public String getPrompt() {
    return prompt;
  }

  @Override
  public String getChoice() {
    return choice;
  }

  @Override
  public void setChoice(String choice) {
    this.choice = choice;
  }
}
