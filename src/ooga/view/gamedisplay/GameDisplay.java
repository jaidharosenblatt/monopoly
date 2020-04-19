package ooga.view.gamedisplay;

import java.util.List;
import javafx.scene.control.Button;
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
  private View view;

  public GameDisplay(View view) {
    this.getStyleClass().add("game-display");
    this.view = view;
    decisionFactory = new DecisionFactory(view);
    takeTurn();
    setSpacing(5);
  }

  public void makeUserDecision(Decision decision, boolean multiChoice) {
    DecisionView decisionView = decisionFactory.getDecision(decision, multiChoice);
    getChildren().add(decisionView);
  }

  private void takeTurn() {
    Button button = new Button("Take turn");
    button.setOnAction(e -> view.handleRoll());
    getChildren().add(button);
  }

  public void displayText(String text) {
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
