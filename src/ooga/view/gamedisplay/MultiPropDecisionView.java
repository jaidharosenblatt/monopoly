package ooga.view.gamedisplay;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.api.objects.MultiPropDecision;
import ooga.util.PropertiesGetter;

/**
 * @author rodrigo.araujo made exact duplication of MultiPlayerDecision (for some reason necessary for
 * backend)
 */
public class MultiPropDecisionView extends Decisions {

  private MultiPropDecision decision;

  /**
   * Constructs a single decision view
   *
   * @param decision    the multiple property choice decision to be made
   * @param playerName  the name to display for whose decision it is
   * @param playerColor the color of that player
   */
  public MultiPropDecisionView(MultiPropDecision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(), playerName, playerColor);
    this.decision = decision;
    addButtons(decision);
    createStage();
  }

  private void addButtons(MultiPropDecision decision) {
    VBox buttons = new VBox();
    buttons.setId("padded-box");
    for (Property choice : decision.getOptions()) {
      RadioButton button = new RadioButton(choice.getTitle());
      button.setOnAction(e -> handleClick(button, choice));
      buttons.getChildren().add(button);
    }
    Button submit = new Button(PropertiesGetter.getPromptFromKey("Submit"));
    submit.setOnAction(event -> closeStage());
    buttons.getChildren().add(submit);
    addElement(buttons);
  }

  private void handleClick(RadioButton button, Property choice) {
    if (button.isSelected()) {
      decision.setChoice(choice);
    }
    if (!button.isSelected()) {
      decision.remChoice(choice);
    }
  }
}
