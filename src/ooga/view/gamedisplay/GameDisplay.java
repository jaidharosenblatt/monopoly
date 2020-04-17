package ooga.view.gamedisplay;

import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.api.view.Decision;
import ooga.view.gamedisplay.decisions.DecisionFactory;
import ooga.view.gamedisplay.decisions.DecisionView;
import ooga.view.View;
import ooga.view.board.Die;

public class GameDisplay extends VBox {

  private DecisionFactory decisionFactory;

  public GameDisplay(View view) {
    decisionFactory = new DecisionFactory(view);
  }

  public void makeUserDecision(Decision decision, boolean multiChoice) {
    DecisionView decisionView = decisionFactory.getDecision(decision, multiChoice);
    getChildren().clear();
    getChildren().add(decisionView);
  }

  public void displayText(String text) {
    getChildren().clear();
    getChildren().add(new Text(text));
  }

  public void displayRoll(List<Integer> rolls) {
    HBox hBox = new HBox();
    for (int die : rolls) {
      hBox.getChildren().add(new Die(die));
    }
    getChildren().add(hBox);
  }
}
