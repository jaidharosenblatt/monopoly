package ooga;

import java.util.List;
import javafx.stage.Stage;
import ooga.api.view.Decision;
import ooga.view.View;
import ooga.view.gamedisplay.decisions.DecisionTester;

public class Controller {

  private View view;

  public Controller(Stage stage) {
    view = new View(stage, this);

    Decision d = new DecisionTester("oops", List.of("choice 1", "choice 2", "choice 3"));
    view.makeUserDecision(d,false);

    view.displayRoll(List.of(1, 2, 3));
  }

  public void submitDecision(List<String> decision) {
    System.out.println("decision is: " + decision);
  }

}
