package ooga.view;

import java.util.List;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.api.view.Decision;
import ooga.view.board.Die;

public class GameDisplay extends VBox {

  private DecisionFactory decisionFactory;

  public GameDisplay(View view) {
    decisionFactory = new DecisionFactory(view);
  }

  public void makeUserDecision(Decision decision) {
    DecisionView decisionView = decisionFactory.getDecision(decision, 1);
    getChildren().clear();
    getChildren().add(decisionView);
    getChildren().add(new Die(1));
  }

  public void displayText(String text){
    getChildren().clear();
    getChildren().add(new Text(text));
  }

  public void displayRoll(List<Integer> rolls) {

  }
}
