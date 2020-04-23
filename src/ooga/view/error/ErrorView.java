package ooga.view.error;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.util.PropertiesGetter;

public class ErrorView {
  private Stage stage;
  private static final String RESOURCES_DEFAULT_CSS = "resources/default.css";

  public ErrorView(String key){
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);

    VBox root = new VBox();
    root.setId("error");

    Text error = new Text(PropertiesGetter.getPromptFromKey("Error"));
    error.setFill(Color.RED);

    Button confirm = new Button(PropertiesGetter.getPromptFromKey("Ok"));
    confirm.setOnAction(event -> stage.close());

    Text message = new Text(PropertiesGetter.getErrorMessageFromKey(key));
    root.getChildren().addAll(error,message, confirm);

    Scene scene = new Scene(root);
    scene.getStylesheets().add(RESOURCES_DEFAULT_CSS);
    stage.setScene(scene);
    stage.showAndWait();
  }
}
