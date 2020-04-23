package ooga.view.splash;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ooga.util.PropertiesGetter;

public class GameTypePicker extends HBox {

  private static final String DATA_PATH = "data";
  private static final String FILE_TO_HIDE = "FOLDER_PURPOSE.md";
  private Map<String, String> gameTypes = new HashMap<>();
  private ComboBox<String> dropdown;
  private List<String> displayTypes = new ArrayList<>();
  private SplashScreen splashScreen;

  protected GameTypePicker(String labelPrompt, SplashScreen splashScreen) {
    this.splashScreen = splashScreen;
    setAlignment(Pos.CENTER);
    createGameTypes();

    Label label = new Label(labelPrompt);

    dropdown = new ComboBox<>();
    dropdown.setItems(FXCollections.observableList(displayTypes));
    dropdown.setValue(displayTypes.get(0));

    Button submit = new Button(PropertiesGetter.getPromptFromKey("Submit"));
    submit.setOnAction(event -> handleSubmit());

    getChildren().addAll(label, dropdown, submit);
  }


  private void createGameTypes() {
    File[] f = new File(DATA_PATH).listFiles();
    for (File file : f) {
      if (file.isFile() && !file.getName().equals(FILE_TO_HIDE)) {
        String boardType = file.getName().split("board")[1];
        String display = boardType.split("\\.")[0];

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
    splashScreen.submit(getPathFromDropdown());
    splashScreen.closeStage();
  }

}
