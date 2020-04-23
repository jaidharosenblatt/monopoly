package ooga.view.splash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.util.PropertiesGetter;

public class NumberPlayersDropdown extends HBox {

  private final static String PLAYERS_TEXT = " " + PropertiesGetter.getPromptFromKey("Players");
  private SplashScreen splashScreen;
  private Map<String, Integer> choices = new HashMap<>();
  private ComboBox<String> dropdown = new ComboBox<>();

  protected NumberPlayersDropdown(int defaultNumPlayers, int minPlayers, int maxPlayers,
      SplashScreen splashScreen) {
    this.splashScreen = splashScreen;
    setId("splash-box");
    List<String> choicesDisplay = new ArrayList<>();

    for (int i = minPlayers; i <= maxPlayers; i++) {
      String display = i + PLAYERS_TEXT;
      choices.put(display, i);
      choicesDisplay.add(display);
    }

    dropdown.setItems(FXCollections.observableList(choicesDisplay));
    dropdown.setValue(defaultNumPlayers + PLAYERS_TEXT);

    Button submit = new Button(PropertiesGetter.getPromptFromKey("AddPlayers"));
    submit.setOnAction(event -> handleSubmit());

    getChildren().addAll(dropdown, submit);
  }

  private void handleSubmit() {
    int numPlayers = choices.get(dropdown.getValue());
    splashScreen.setNumPlayers(numPlayers);
  }
}
