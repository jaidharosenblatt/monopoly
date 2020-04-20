package ooga.view.gamedisplay;


import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public class DecisionView extends VBox {

  private static final double WIDTH = 200;

  public DecisionView(Decision decision) {
    this.getStyleClass().add("decision-display");

    setPrefWidth(WIDTH);
    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);
    setSpacing(5);

    for (String choice : decision.getOptions()) {
      Button button = new Button(choice);
      button.setOnAction(e -> decision.setChoice(choice));
      getChildren().add(button);
    }
  }
}
