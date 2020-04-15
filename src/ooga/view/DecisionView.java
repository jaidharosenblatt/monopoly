package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public class DecisionView extends StackPane {

  private static final double WIDTH = 200;
  private List<Integer> choices = new ArrayList<>();
  private Decision decision;
  private View view;

  public DecisionView(Decision decision, View view) {
    this.view = view;
    setPrefWidth(WIDTH);
    setBackground(
        new Background(new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

    this.decision = decision;
    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);

    VBox box = new VBox();
    for (String choice : decision.getOptions()) {
      Button button = new Button(choice);
      button.setOnAction(e -> setChoice(choice));
      box.getChildren().add(button);
    }

    Button submit = new Button("Submit");
    submit.setOnAction(e -> view.submitDecision(choices));
    box.getChildren().add(submit);

    getChildren().add(box);
  }

  private void setChoice(String choice) {
    choices.add(decision.getOptions().indexOf(choice));
  }

}
