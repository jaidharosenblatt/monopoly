package ooga.view.gamedisplay;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.api.objects.Decision;

public class DecisionView extends Decisions{

  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private Decision decision;

  public DecisionView(Decision decision, String playerName, Color playerColor) {
    super(decision.getPrompt(),playerName,playerColor);
    this.decision = decision;
    addButtons(decision);
    createStage();
  }

  private void addButtons(Decision decision) {
    HBox buttons = new HBox();
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(5);
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
