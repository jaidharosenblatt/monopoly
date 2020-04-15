package ooga.view;

import javafx.scene.layout.Pane;
import ooga.api.view.Decision;

public class DecisionFactory extends Pane {

  private View view;

  public DecisionFactory(View view) {
    this.view = view;
  }

  public DecisionView getDecision(Decision decision, int numberOfChoices){
    if (numberOfChoices == 2){
      return null;
    }
    return new MultiDecisionView(decision,view);
  }


}
