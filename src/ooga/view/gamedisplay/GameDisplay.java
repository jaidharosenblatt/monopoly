package ooga.view.gamedisplay;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.api.view.Decision;
import ooga.view.gamedisplay.decisions.DecisionFactory;
import ooga.view.gamedisplay.decisions.DecisionView;
import ooga.view.View;

public class GameDisplay extends VBox {

  private static final double PADDING = 5;
  private DecisionFactory decisionFactory;
  private View view;

  public GameDisplay(View view) {
    this.getStyleClass().add("game-display");
    this.view = view;
    decisionFactory = new DecisionFactory(view);
    createButtons();
    setSpacing(PADDING);

  }

  public void makeUserDecision(Decision decision, boolean multiChoice) {
    DecisionView decisionView = decisionFactory.getDecision(decision, multiChoice);
    getChildren().add(decisionView);
  }

  private void createButtons() {
    HBox hBox = new HBox();
    hBox.setSpacing(PADDING);
    Button turn = new Button("Take turn");
    turn.setOnAction(e -> view.handleRoll());

    Button mortgage = new Button("Mortgage");
    mortgage.setOnAction(e -> view.handleMortgage());

    Button trade = new Button("Trade");
    trade.setOnAction(e -> view.handleTrade());

    hBox.getChildren().addAll(turn, mortgage, trade);

    getChildren().add(hBox);
  }

  public void displayText(String text) {
    getChildren().add(new Text(text));
  }

}
