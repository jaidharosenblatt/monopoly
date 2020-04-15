package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public abstract class DecisionView extends VBox {

  private static final double WIDTH = 200;
  private List<String> choices = new ArrayList<>();
  private Decision decision;

  public DecisionView(Decision decision) {
    this.decision = decision;

    setPrefWidth(WIDTH);
    setBackground(
        new Background(new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);
  }

  protected void setChoice(String choice) {
    if (choices.contains(choice)) {
      choices.remove(choice);
    } else {
      choices.add(choice);
    }
  }

  protected List<String> getChoices() {
    return choices;
  }
}
