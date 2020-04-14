package ooga.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.api.view.Decision;

public class DecisionView extends StackPane {

  private static final double WIDTH = 200;
  private List<Integer> choices = new ArrayList<>();
  private Decision decision;
  private Method submitAction;

  public DecisionView(Decision decision, Method submitAction) {
    setPrefWidth(WIDTH);
    setBackground(new Background(new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

    this.decision = decision;
    this.submitAction = submitAction;
    Text text = new Text(decision.getPrompt());
    text.setWrappingWidth(WIDTH);
    getChildren().add(text);

    VBox box= new VBox();
    for (String choice : decision.getOptions()) {
      Button button = new Button(choice);
      button.setOnAction(e -> setChoice(choice));
      box.getChildren().add(button);
    }

    getChildren().add(box);
  }

  public List<Integer> getChoiceResponses(){
    return choices;
  }

  private void setChoice(String choice){
    try {
      submitAction.invoke(choices);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    choices.add(decision.getOptions().indexOf(choice));

  }

}
