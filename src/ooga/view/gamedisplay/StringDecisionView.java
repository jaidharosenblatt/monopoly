package ooga.view.gamedisplay;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.api.objects.StringDecision;
import ooga.util.PropertiesGetter;

/**
 * @author jaidharosenblatt create a decision to display to the user. Takes in a decision object to
 * get the prompt and options and sets a inputted string to that decision
 */
public class StringDecisionView extends Decisions {

  private TextArea input;
  private StringDecision decision;
  private static final double INPUT_HEIGHT = 20;
  private static final double INPUT_WIDTH = 200;

  /**
   * Constructs a single decision view
   *
   * @param decision    the string decision to be made
   * @param playerName  the name to display for whose decision it is
   * @param playerColor the color of that player
   */
  public StringDecisionView(StringDecision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(), playerName, playerColor);
    this.decision = decision;
    addInput();
    createStage();
  }

  private void addInput() {
    VBox buttons = new VBox();
    buttons.setId("padded-Box");
    input = new TextArea();
    input.setPrefSize(INPUT_WIDTH, INPUT_HEIGHT);

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
