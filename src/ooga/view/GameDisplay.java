package ooga.view;

import java.util.List;
import javafx.scene.layout.VBox;
import ooga.api.view.Decision;

public class GameDisplay extends VBox {

  private DecisionFactory decisionFactory;

  public GameDisplay(View view) {
    decisionFactory = new DecisionFactory(view);
  }

  public void makeUserDecision(Decision decision) {
    DecisionView decisionView = decisionFactory.getDecision(decision, 1);
    getChildren().clear();
    getChildren().add(decisionView);
  }
}
