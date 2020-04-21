package ooga.view.gamedisplay;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.api.objects.MultiDecision;

public class MultiDecisionView {

    private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";
    private VBox vBox = new VBox();
    private MultiDecision decision;
    private Stage stage;

    public MultiDecisionView(MultiDecision decision, String playerName, Color playerColor) {
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

    private void addButtons(MultiDecision decision) {
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(5);
        for (Property choice : decision.getOptions()) {
            RadioButton button = new RadioButton(choice.getTitle());
            button.setOnAction(e -> handleClick(button, choice));
            buttons.getChildren().add(button);
        }
        Button submit = new Button("Submit");
        submit.setOnAction(event -> finishDecision());
        buttons.getChildren().add(submit);
        vBox.getChildren().add(buttons);
    }

    private void handleClick(RadioButton button, Property choice) {
        if (button.isSelected()) {
            decision.setChoice(choice);
        }
        if (!button.isSelected()) {
            decision.remChoice(choice);
        }
    }

    private void finishDecision() {
        stage.close();
    }
}
