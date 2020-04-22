package ooga.view.gamedisplay;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.api.objects.Decision;
import ooga.api.objects.StringDecision;

public class StringDecisionView {

  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private VBox vBox = new VBox();
  private StringDecision decision;
  private Stage stage;

  public StringDecisionView(StringDecision decision, String playerName, Color playerColor) {
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

    vBox.getChildren().addAll(playerText, promptText);
    vBox.setSpacing(5);

    stage.setScene(scene);
    stage.showAndWait();
  }

}
