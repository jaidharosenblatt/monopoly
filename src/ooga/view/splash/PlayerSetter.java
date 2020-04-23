package ooga.view.splash;

import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class PlayerSetter extends HBox {

  private ColorPicker picker;
  private TextArea input;
  private static final double INPUT_WIDTH = 80;
  private static final double INPUT_HEIGHT = 8;

  protected PlayerSetter(String colorPrompt, String namePrompt, String defaultName) {
    setAlignment(Pos.CENTER);
    Label colorLabel = new Label(colorPrompt);
    Label nameLabel = new Label(namePrompt);

    picker = new ColorPicker();
    input = new TextArea();
    input.setText(defaultName);
    input.setPrefSize(INPUT_WIDTH,INPUT_HEIGHT);

    getChildren().addAll(colorLabel,picker,nameLabel, input);
  }

  protected String getName(){
    return input.getText();
  }

  protected String getColor(){
    return picker.getValue().toString();
  }

}
