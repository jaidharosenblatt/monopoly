package ooga.view.splash;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class PlayerSetter extends HBox {

  private ColorPicker picker;
  private TextArea input;
  private static final double INPUT_WIDTH = 80;
  private static final double INPUT_HEIGHT = 10;

  protected PlayerSetter(String prompt) {
    Label label = new Label(prompt);
    picker = new ColorPicker();
    input = new TextArea();
    input.setPrefSize(INPUT_WIDTH,INPUT_HEIGHT);

    getChildren().addAll(label,picker,input);
  }

  protected String getName(){
    return input.getText();
  }

  protected String getColor(){
    return picker.getValue().toString();
  }

}
