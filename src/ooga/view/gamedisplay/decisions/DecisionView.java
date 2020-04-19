package ooga.view.gamedisplay.decisions;


import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public abstract class DecisionView extends VBox {

  private static final double WIDTH = 200;

  public DecisionView(Decision decision) {
    this.getStyleClass().add("decision-display");

    setPrefWidth(WIDTH);
    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);
    setSpacing(5);
  }
}
