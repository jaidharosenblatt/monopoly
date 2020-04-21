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

public class DecisionView {

  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private VBox vBox = new VBox();
  private Decision decision;
  private Stage stage;

  public DecisionView(Decision decision, String playerName, Color playerColor) {
    this.decision = decision;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    Scene scene = new Scene(vBox);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);

    vBox.setAlignment(Pos.CENTER);
    vBox.setId("decision-display");

    Text playerText = new Text(playerName);
    playerText.setId("name");
    playerText.setFill(playerColor);

    Text promptText = new Text(decision.getPrompt());

    vBox.getChildren().addAll(playerText,promptText);
    vBox.setSpacing(5);

    addButtons(decision);

    stage.setScene(scene);
    stage.showAndWait();
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

    vBox.getChildren().add(buttons);
  }

  private void handleClick(String choice){
    decision.setChoice(choice);
    stage.close();
  }
}
