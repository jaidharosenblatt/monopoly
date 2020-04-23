package ooga.view.gamedisplay;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.BackEnd.GameLogic.Decisions.MultiPlayerDecision;
import ooga.BackEnd.GameObjects.Player;
import ooga.util.PropertiesGetter;

public class MultiPlayerDecisionView extends Decisions {

  private MultiPlayerDecision decision;
  private static final int PADDING = 5;

  public MultiPlayerDecisionView(MultiPlayerDecision decision, String playerName,
      Color playerColor) {
    super(decision.getPrompt(), playerName, playerColor);
    this.decision = decision;
    addButtons(decision);
    createStage();
  }

  private void addButtons(MultiPlayerDecision decision) {
    VBox buttons = new VBox();
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(PADDING);
    for (Player choice : decision.getOptions()) {
      RadioButton button = new RadioButton(choice.getName());
      button.setOnAction(e -> handleClick(button, choice));
      buttons.getChildren().add(button);
    }
    Button submit = new Button(PropertiesGetter.getPromptFromKey("Submit"));
    submit.setOnAction(event -> closeStage());
    buttons.getChildren().add(submit);
    addElement(buttons);
  }

  private void handleClick(RadioButton button, Player choice) {
    if (button.isSelected()) {
      decision.setChoice(choice);
    }
    if (!button.isSelected()) {
      decision.remChoice(choice);
    }
  }
}
