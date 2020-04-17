package ooga.view.gamedisplay.decisions;

import javafx.scene.layout.Pane;
import ooga.api.view.Decision;
import ooga.view.View;

public class DecisionFactory extends Pane {

  private View view;

  public DecisionFactory(View view) {
    this.view = view;
  }

  public DecisionView getDecision(Decision decision, int numberOfChoices) {
    if (numberOfChoices == 2) {
      return new TwoDecisionView(decision, view);
    }
    return new MultiDecisionView(decision, view);
  }


}
