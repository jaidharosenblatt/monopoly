package ooga.view.splash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class NumberPlayersDropdown extends VBox {

  private final static String PLAYERS_TEXT = " players";
  private SplashScreen splashScreen;
  private Map<String, Integer> choices;
  private ComboBox<String> dropdown = new ComboBox<>();

  public NumberPlayersDropdown(int defaultNumPlayers, int maxPlayers, SplashScreen splashScreen) {
    this.splashScreen = splashScreen;
    choices = new HashMap<>();
    List<String> choicesDisplay = new ArrayList<>();

    for (int i = 1; i <= maxPlayers; i++) {
      String display = i + PLAYERS_TEXT;
      choices.put(display, i);
      choicesDisplay.add(display);
    }

    dropdown.setItems(FXCollections.observableList(choicesDisplay));
    dropdown.setValue(defaultNumPlayers + PLAYERS_TEXT);

    Button submit = new Button("Submit");
    submit.setOnAction(event -> handleSubmit());

    getChildren().addAll(dropdown, submit);
  }

  private void handleSubmit() {
    int numPlayers = choices.get(dropdown.getValue());
    splashScreen.setNumPlayers(numPlayers);
  }
}
