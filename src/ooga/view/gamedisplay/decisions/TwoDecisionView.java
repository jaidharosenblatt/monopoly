package ooga.view.gamedisplay.decisions;

import java.util.List;
import javafx.scene.control.Button;
import ooga.api.view.Decision;
import ooga.view.View;

public class TwoDecisionView extends DecisionView {

  public TwoDecisionView(Decision decision, View view) {
    super(decision);

    for (String choice : decision.getOptions()) {
      Button button = new Button(choice);
      button.setOnAction(e -> view.submitDecision(List.of(choice)));
      getChildren().add(button);
    }
  }

}
