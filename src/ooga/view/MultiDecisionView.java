package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public class MultiDecisionView extends VBox {

  private static final double WIDTH = 200;
  private List<Integer> choices = new ArrayList<>();
  private Decision decision;

  public MultiDecisionView(Decision decision, View view) {
    this.decision = decision;

    setPrefWidth(WIDTH);
    setBackground(
        new Background(new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);

    for (String choice : decision.getOptions()) {
      RadioButton button = new RadioButton(choice);
      button.setOnAction(e -> setChoice(choice));
      getChildren().add(button);
    }

    Button submit = new Button("Submit");
    submit.setOnAction(e -> view.submitDecision(choices));
    getChildren().add(submit);

  }

  private void setChoice(String choice) {
    choices.add(decision.getOptions().indexOf(choice));
  }

}
