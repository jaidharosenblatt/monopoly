package ooga.view.gamedisplay.decisions;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import ooga.api.view.Decision;
import ooga.view.View;

public class MultiDecisionView extends DecisionView {
  private List<String> choices = new ArrayList<>();

  public MultiDecisionView(Decision decision, View view) {
    super(decision);

    for (String choice : decision.getOptions()) {
      RadioButton button = new RadioButton(choice);
      button.setOnAction(e -> setChoice(choice));
      getChildren().add(button);
    }

    Button submit = new Button("Submit");
    submit.setOnAction(e -> decision.setChoices(choices));
    getChildren().add(submit);
  }

  private void setChoice(String choice) {
    if (choices.contains(choice)) {
      choices.remove(choice);
    } else {
      choices.add(choice);
    }
  }

}
