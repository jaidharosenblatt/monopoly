package ooga.view.gamedisplay;


import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.api.view.Decision;

public class DecisionView {

  private HBox hBox = new HBox();
  private Decision decision;
  private Stage stage;

  public DecisionView(Decision decision) {
    this.decision = decision;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    Scene scene = new Scene(hBox);
    hBox.getStyleClass().add("decision-display");

    Text text = new Text(decision.getPrompt());

    hBox.getChildren().add(text);
    hBox.setSpacing(5);

    for (String choice : decision.getOptions()) {
      Button button = new Button(choice);
      button.setOnAction(e -> handleClick(choice));
      hBox.getChildren().add(button);
    }
    stage.setScene(scene);
    stage.showAndWait();
  }

  private void handleClick(String choice){
    decision.setChoice(choice);
    stage.close();
  }
}
