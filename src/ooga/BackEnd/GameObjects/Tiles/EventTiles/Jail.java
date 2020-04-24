package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.util.PropertiesGetter;

import java.util.List;

/**
 * @author rodrigoaraujo Event tile for jail
 */

public class Jail extends Event {

    private static final List<String> options = List.of(PropertiesGetter.getPromptFromKey("Yes"),PropertiesGetter.getPromptFromKey("No"));
    private static final List<String> option = List.of(PropertiesGetter.getPromptFromKey("Ok"));

    /**
     * default constructor for parsing
     */

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
                Decision d = new Decision(PropertiesGetter.getPromptFromKey("jailprompt1"),options);
                getView().makeUserDecision(d);

                if (d.getChoice().equals(PropertiesGetter.getPromptFromKey("Yes"))) {
                    this.visiting.useJailFreeCard();
                    this.visiting.setFree();
                    this.visiting.rollDice();
                    this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                    return;
                }
            }
            if (this.visiting.getJailTurn() < 2) {
                Decision d = new Decision(PropertiesGetter.getPromptFromKey("jailprompt2"),options);
                getView().makeUserDecision(d);
                if (d.getChoice().equals(PropertiesGetter.getPromptFromKey("Yes"))) {
                    this.visiting.payBail();
                    this.visiting.setFree();
                    this.visiting.rollDice();
                    this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                }
                if (d.getChoice().equals(PropertiesGetter.getPromptFromKey("No"))) {
                    this.visiting.addJailTurn();
                    this.visiting.rollDice();
                    if (this.visiting.dice1 == this.visiting.dice2) {
                        this.visiting.setFree();
                        this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                    }
                    else {

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
        else {
            Decision d = new Decision(PropertiesGetter.getPromptFromKey("jailprompt3"),option);
            getView().makeUserDecision(d);
        }
    }
}
