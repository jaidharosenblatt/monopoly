package ooga.view.gamedisplay;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.api.objects.StringDecision;
import ooga.util.PropertiesGetter;

public class StringDecisionView extends Decisions {

  private TextArea input;
  private StringDecision decision;
  private static final double INPUT_HEIGHT = 20;
  private static final double INPUT_WIDTH = 200;
  private static final int PADDING = 5;


  public StringDecisionView(StringDecision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(), playerName, playerColor);
    this.decision = decision;
    addInput();
    createStage();
  }

  private void addInput() {
    VBox buttons = new VBox();
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(PADDING);
    input = new TextArea();
    input.setPrefSize(INPUT_WIDTH,INPUT_HEIGHT);

    Button submit = new Button(PropertiesGetter.getPromptFromKey("Submit"));
    submit.setOnAction(event -> submit());

    buttons.getChildren().addAll(input, submit);
    addElement(buttons);
  }

  private void submit() {
    decision.setChoice(input.getText());
    closeStage();
  }

}
