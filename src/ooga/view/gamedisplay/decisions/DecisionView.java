package ooga.view.gamedisplay.decisions;


import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public abstract class DecisionView extends VBox {

  private static final double WIDTH = 200;

  public DecisionView(Decision decision) {
    setPrefWidth(WIDTH);
    setBackground(
        new Background(new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);
  }
}
