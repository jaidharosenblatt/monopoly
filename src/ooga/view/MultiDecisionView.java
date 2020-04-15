package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import ooga.api.view.Decision;

public class MultiDecisionView extends DecisionView {

  public MultiDecisionView(Decision decision, View view) {
    super(decision);

    for (String choice : decision.getOptions()) {
      RadioButton button = new RadioButton(choice);
      button.setOnAction(e -> setChoice(choice));
      getChildren().add(button);
    }

    Button submit = new Button("Submit");
    submit.setOnAction(e -> view.submitDecision(getChoices()));
    getChildren().add(submit);
  }

}
