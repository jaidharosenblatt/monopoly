package ooga.view.gamedisplay;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ooga.view.View;

public class TurnActionButtons extends HBox {

  private static final double PADDING = 5;

  public TurnActionButtons(View view) {
    setId("turn-buttons");
    setSpacing(PADDING);

    Button turn = new Button("Take turn");
    turn.setOnAction(e -> view.handleRoll());

    Button build = new Button("Build");
    build.setOnAction(e -> view.handleBuild());

    Button sell = new Button("Sell");
    sell.setOnAction(e -> view.handleSell());

    Button mortgage = new Button("Mortgage");
    mortgage.setOnAction(e -> view.handleMortgage());

    Button unmortgage = new Button("Unmortgage");
    unmortgage.setOnAction(e -> view.handleUnmortgage());

    Button trade = new Button("Trade");
    trade.setOnAction(e -> view.handleTrade());

    getChildren().addAll(turn, build, sell, mortgage, unmortgage, trade);
  }
}
