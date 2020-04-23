package ooga.view.gamedisplay;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import ooga.api.objects.Decision;

public class DecisionView extends Decisions{

  private static final int PADDING = 5;
  private Decision decision;

  public DecisionView(Decision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(),playerName,playerColor);
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

  private void handleClick(String choice){
    decision.setChoice(choice);
    closeStage();
  }
}
