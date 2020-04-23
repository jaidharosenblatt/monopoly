package ooga.view.gamedisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.view.View;

public class GameTypePicker extends VBox {

  private static final String DATA_PATH = "data";
  private static final String FILE_TO_HIDE = "FOLDER_PURPOSE.md";
  private Map<String, String> gameTypes = new HashMap<>();
  private ComboBox<String> dropdown;
  private List<String> displayTypes = new ArrayList<>();
  private Stage stage;
  private View view;

  public GameTypePicker(Stage stage, View view) {
    this.view = view;
    this.stage = stage;

    setAlignment(Pos.CENTER);
    createGameTypes();

    dropdown = new ComboBox<>();
    dropdown.setItems(FXCollections.observableList(displayTypes));

    Button submit = new Button("Submit");
    submit.setOnAction(event -> handleSubmit());

    getChildren().addAll(dropdown, submit);
  }


  private void createGameTypes() {
    File[] f = new File(DATA_PATH).listFiles();
    for (File file : f) {
      if (file.isFile() && !file.getName().equals(FILE_TO_HIDE)) {
        String display = file.getName().split("\\.")[0];
        String path = file.getPath();
        gameTypes.put(display, path);
        displayTypes.add(display);
      }
    }
  }

  private String getPathFromDropdown(){
    return  gameTypes.get(dropdown.getValue());
  }

  private void handleSubmit() {
    view.setGameType(getPathFromDropdown());
    stage.close();
  }

}
