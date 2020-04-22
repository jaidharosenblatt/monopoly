package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameLogic.Decisions.Decision;

import java.util.List;

public class Jail extends Event {

    public Jail() {}

    public Jail(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        if (this.visiting.isJailed()) {
            System.out.println(this.visiting.getName() + " is currently in Jail.");
            if (this.visiting.hasJailFreeCards()) {
                List<String> options = List.of("Yes","No");
                Decision d = new Decision("Would you like to use your Get Out of Jail Free Card?",options);
                getView().makeUserDecision(d);

                if (d.getChoice().equals("Yes")) {
                    this.visiting.useJailFreeCard();
                    this.visiting.setFree();
                    this.visiting.rollDice();
                    this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                    return;
                }
            }
            if (this.visiting.getJailTurn() < 2) {
                List<String> options = List.of("Yes","No");
                Decision d = new Decision("Would you like to bail out of jail?",options);
                getView().makeUserDecision(d);
                if (d.getChoice().equals("Yes")) {
                    this.visiting.payBail();
                    this.visiting.setFree();
                    this.visiting.rollDice();
                    this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                }
                if (d.getChoice().equals("No")) {
                    this.visiting.addJailTurn();
                    this.visiting.rollDice();
                    if (this.visiting.dice1 == this.visiting.dice2) {
                        this.visiting.setFree();
                        this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                    }
                    else {
                        List<String> option = List.of("OK");
                        Decision d1 = new Decision(this.visiting.getName() + " remains in Jail",option);
                        view.makeUserDecision(d1);
                    }
                }
            }
            else {
                this.visiting.payBail();
                this.visiting.setFree();
                this.visiting.rollDice();
                this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
            }
        }
    }
}
