package ooga.view.gamedisplay;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author jaidharosenblatt abstract class that creates a window prompting the user to make a
 * decision.
 */
public abstract class Decisions {

  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
  private VBox vBox = new VBox();
  private Stage stage;

  /**
   * Constructs a single decision view
   * @param prompt the prompt to display to the user (comes from decision object)
   * @param playerName the name to display for whose decision it is
   * @param playerColor the color of that player
   */
  public Decisions(String prompt, String playerName, Color playerColor) {
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    vBox.setId("decision-display");

    Text playerText = new Text(playerName);
    playerText.setId("name");
    playerText.setFill(playerColor);

    Text promptText = new Text(prompt);

    vBox.getChildren().addAll(playerText, promptText);
    vBox.setSpacing(5);
  }

  protected void createStage() {
    Scene scene = new Scene(vBox);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }

  protected void addElement(Node e) {
    vBox.getChildren().add(e);
  }

  protected void closeStage() {
    stage.close();
  }
}
