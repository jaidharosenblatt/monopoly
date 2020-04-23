package ooga.view.gamedisplay;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import ooga.api.objects.Decision;

/**
 * @author jaidharosenblatt create a single choice decision to display to the user. Takes in a
 * decision object to get the prompt and options and sets the choice of that user
 */
public class DecisionView extends Decisions {

  private Decision decision;

  /**
   * Constructs a single decision view
   * @param decision the single choice decision to be made
   * @param playerName the name to display for whose decision it is
   * @param playerColor the color of that player
   */
  public DecisionView(Decision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(), playerName, playerColor);
    this.decision = decision;
    addButtons(decision);
    createStage();
  }

  private void addButtons(Decision decision) {
    HBox buttons = new HBox();
    buttons.setId("padded-box");
    for (String choice : decision.getOptions()) {
      Button button = new Button(choice);
      button.setOnAction(e -> handleClick(choice));
      buttons.getChildren().add(button);
    }

    addElement(buttons);
  }

  private void handleClick(String choice) {
    decision.setChoice(choice);
    closeStage();
  }
}
