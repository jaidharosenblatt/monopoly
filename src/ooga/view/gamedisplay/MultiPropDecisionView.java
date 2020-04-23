package ooga.view.gamedisplay;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.api.objects.MultiPropDecision;
import ooga.util.PropertiesGetter;

public class MultiPropDecisionView extends Decisions {

  private MultiPropDecision decision;

  public MultiPropDecisionView(MultiPropDecision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(),playerName,playerColor);
    this.decision = decision;
    addButtons(decision);
    createStage();
  }

  private void addButtons(MultiPropDecision decision) {
    VBox buttons = new VBox();
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(5);
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
