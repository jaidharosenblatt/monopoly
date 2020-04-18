package ooga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.stage.Stage;
import ooga.api.view.Decision;
import ooga.api.view.PlayerInfo;
import ooga.view.PlayerTester;
import ooga.view.View;
import ooga.view.gamedisplay.decisions.DecisionTester;

public class Controller {

  private View view;
  private Map<PlayerInfo, Integer> playerPositions;
  private int counter = 0;

  public Controller(Stage stage) {
    createPlayerInfo();
    view = new View(stage, this, playerPositions);

    Decision d = new DecisionTester("oops", List.of("choice 1", "choice 2", "choice 3"));
    view.makeUserDecision(d,false);
  }

  /**
   * For testing
   */
  private void createPlayerInfo(){
    playerPositions = new HashMap<>();
    for (int i =0; i<4;i++){
      PlayerInfo player = new PlayerTester(0, i, "2E86AB");
      playerPositions.put(player,0);
    }
  }

  private List<Integer> getRandomRolls(){
    List<Integer> rolls = new ArrayList<>();
    Random r = new Random();
    rolls.add(r.nextInt(5)+1);
    rolls.add(r.nextInt(5)+1);
    System.out.println(rolls);
    return rolls;
  }

  public void submitDecision(List<String> decision) {
    System.out.println("decision is: " + decision);
  }

  public void handleRoll() {
    System.out.println("moving to: " + counter);
    PlayerInfo randomPlayer = playerPositions.keySet().iterator().next();
    view.movePlayer(randomPlayer, counter++);
    view.displayRoll(getRandomRolls());
  }


}
