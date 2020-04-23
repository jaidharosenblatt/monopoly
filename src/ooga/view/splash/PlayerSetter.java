package ooga.view.splash;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @author jaidharosenblatt Labeled color picker and input box to allow customization of the name
 * and color of user to be displayed in the game
 */
public class PlayerSetter extends HBox {

  private ColorPicker picker;
  private TextArea input;

  protected PlayerSetter(String colorPrompt, String namePrompt, String defaultName) {
    setId("padded-box");
    Label colorLabel = new Label(colorPrompt);
    Label nameLabel = new Label(namePrompt);

    picker = new ColorPicker();
    picker.setValue(getRandomColor());

    input = new TextArea();
    input.setText(defaultName);
    input.setId("splash-input");

    getChildren().addAll(colorLabel, picker, nameLabel, input);
  }

  private Color getRandomColor() {
    return Color.color(Math.random(), Math.random(), Math.random());
  }

  protected String getName() {
    return input.getText();
  }

  protected String getColor() {
    return picker.getValue().toString();
  }

}
