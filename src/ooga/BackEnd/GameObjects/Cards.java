package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.util.PropertiesGetter;
import ooga.view.View;

import java.util.List;

public class Cards {

    private static final int JAIL_INDEX = 10;
    private static final int GO_INDEX = 0;
    private static final int ILLINOIS_INDEX = 24;
    private static final int ST_CHARLES_INDEX = 11;
    private static final int BOARDWALK_INDEX = 39;
    private static final int WATER_INDEX = 28;
    private static final int ELECTRIC_INDEX = 12;
    private static final int READING_RR_INDEX = 5;
    private static final int BO_RR_INDEX = 25;
    private static final int SHORT_RR_INDEX = 35;
    private static final int CROSS_GO = 200;
    private static final List<String> option = List.of(PropertiesGetter.getPromptFromKey("Ok"));
    private static final List<String> options = List.of(PropertiesGetter.getPromptFromKey("Yes"), PropertiesGetter.getPromptFromKey("No"));

    private View view;
    private Player user;
    private String type;
    private List<Property> properties;
    private List<Player> players;

    public Cards(String type, Player user, List<Property> properties, List<Player> players, View view) {
        this.type = type;
        this.user = user;
        this.properties = properties;
        this.players = players;
        this.view = view;
    }

    public void action() {
        Decision d = new Decision(user.getName() + " just pulled a " + this.type + " card", option);
        view.makeUserDecision(d);
        if (this.type.equals(PropertiesGetter.getPromptFromKey("cardtype1"))) {
            int probality = (int) (Math.random() * 16) + 1;
            switch (probality) {
                case 1:
                    Decision d1 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt1"), option);
                    view.makeUserDecision(d1);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
                    Decision d2 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt2"), option);
                    view.makeUserDecision(d2);
                    if (user.getPositionOnBoard() > ILLINOIS_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(ILLINOIS_INDEX);
                    break;
                case 3:
                    Decision d3 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt3"), option);
                    view.makeUserDecision(d3);
                    if (user.getPositionOnBoard() > ST_CHARLES_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(ST_CHARLES_INDEX);
                    break;
                case 4:
                    Decision d4 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt4"), option);
                    view.makeUserDecision(d4);
                    if (user.getPositionOnBoard() > 20) {
                        payUtil10(WATER_INDEX);
                    }
                    else {
                        payUtil10(ELECTRIC_INDEX);
                    }
                    break;
                case 5:
                case 6:
                    Decision d5 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt5"), option);
                    view.makeUserDecision(d5);
                    if (user.getPositionOnBoard() < 10) {
                        payRR2(READING_RR_INDEX);
                    }
                    else if (user.getPositionOnBoard() < 30) {
                        payRR2(BO_RR_INDEX);
                    }
                    else {
                        payRR2(SHORT_RR_INDEX);
                    }
                    break;
                case 7:
                    Decision d6 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt6"), option);
                    view.makeUserDecision(d6);
                    user.receive(50);
                    break;
                case 8:
                    Decision d7 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt7"), option);
                    view.makeUserDecision(d7);
                    user.getJailFreeCard();
                    break;
                case 9:
                    Decision d8 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt8"), option);
                    view.makeUserDecision(d8);
                    int newTile = this.user.getPositionOnBoard() - 3;
                    this.user.moveTo(newTile);
                    break;
                case 10:
                    Decision d9 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt9"), option);
                    view.makeUserDecision(d9);
                    user.setJailed();
                    break;
                case 11:
                    Decision d10 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt10"), option);
                    view.makeUserDecision(d10);
                    user.payBank(user.getHouses() * 25, false);
                    break;
                case 12:
                    Decision d11 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt11"), option);
                    view.makeUserDecision(d11);
                    user.payBank(15, false);
                    break;
                case 13:
                    Decision d12 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt12"), option);
                    view.makeUserDecision(d12);
                    if (user.getPositionOnBoard() > READING_RR_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(READING_RR_INDEX);
                    break;
                case 14:
                    Decision d13 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt13"), option);
                    view.makeUserDecision(d13);
                    user.moveTo(BOARDWALK_INDEX);
                    break;
                case 15:
                    Decision d14 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt14"), option);
                    view.makeUserDecision(d14);
                    for (Player p : players) {
                        if (user != p) {
                            user.payPlayer(p, 50, false);
                        }
                    }
                    break;
                case 16:
                    Decision d15 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt15"), option);
                    view.makeUserDecision(d15);
                    user.receive(150);
                    break;
                case 17:
                    Decision d16 = new Decision( PropertiesGetter.getPromptFromKey("cardprompt16"), option);
                    view.makeUserDecision(d16);
                    user.receive(100);
                    break;
            }
        }
        if (this.type.equals(PropertiesGetter.getPromptFromKey("cardtype2"))) {
            int probality = (int) (Math.random() * 17) + 1;
            switch (probality) {
                case 1:
                    Decision d1 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt1"), option);
                    view.makeUserDecision(d1);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
                    Decision d2 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt17"), option);
                    view.makeUserDecision(d2);
                    user.receive(CROSS_GO);
                    break;
                case 3:
                    Decision d3 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt18"), option);
                    view.makeUserDecision(d3);
                    user.payBank(50, false);
                    break;
                case 4:
                    Decision d4 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt19"), option);
                    view.makeUserDecision(d4);
                    user.receive(50);
                    break;
                case 5:
                    Decision d5 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt7"), option);
                    view.makeUserDecision(d5);
                    user.getJailFreeCard();
                    break;
                case 6:
                    Decision d6 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt9"), option);
                    view.makeUserDecision(d6);
                    user.setJailed();
                    break;
                case 7:
                    Decision d7 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt20"), option);
                    view.makeUserDecision(d7);
                    for (Player p : players) {
                        if (user != p) {
                            p.payPlayer(user, 50, false);
                        }
                    }
                    break;
                case 8:
                    Decision d8 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt21"), option);
                    view.makeUserDecision(d8);
                    user.receive(100);
                    break;
                case 9:
                    Decision d9 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt22"), option);
                    view.makeUserDecision(d9);
                    user.receive(20);
                    break;
                case 10:
                    Decision d10 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt23"), option);
                    view.makeUserDecision(d10);
                    for (Player p : players) {
                        if (user != p) {
                            p.payPlayer(user, 10, false);
                        }
                    }
                    break;
                case 11:
                    Decision d11 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt24"), option);
                    view.makeUserDecision(d11);
                    user.receive(100);
                    break;
                case 12:
                    Decision d12 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt25"), option);
                    view.makeUserDecision(d12);
                    user.payBank(50, false);
                    break;
                case 13:
                    Decision d13 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt26"), option);
                    view.makeUserDecision(d13);
                    user.payBank(50, false);
                    break;
                case 14:
                    Decision d14 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt27"), option);
                    view.makeUserDecision(d14);
                    user.receive(25);
                    break;
                case 15:
                    Decision d15 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt28"), option);
                    view.makeUserDecision(d15);
                    user.payBank(user.getHouses() * 40, false);
                    break;
                case 16:
                    Decision d16 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt29"), option);
                    view.makeUserDecision(d16);
                    user.receive(10);
                    break;
                case 17:
                    Decision d17 = new Decision(PropertiesGetter.getPromptFromKey("cardprompt30"), option);
                    view.makeUserDecision(d17);
                    user.receive(100);
                    break;
            }
        }
    }

    private void payUtil10(int utilTile) {
        this.user.setTile(utilTile);
        view.movePlayer(this.user,utilTile);
        for (Property p : this.properties) {
            if (p.getBoardIndex() == utilTile) {
                if (!p.isOwned()) {
                    Decision d = new Decision("Would you like to buy " + p.getTitle() + " for $" + p.getCost() + "?", options);
                    view.makeUserDecision(d);
                    if (d.getChoice().equals("Yes")) {
                        if (this.user.getCashBalance() >= p.getCost()) {
                            p.setOwner(this.user);
                            this.user.buyProperty(p);
                        }
                    }
                }
                else if (this.user != p.getOwner() && !p.isMortgaged()) {
                    this.user.rollDice();
                    this.user.payPlayer(p.getOwner(), (this.user.dice1 + this.user.dice2) * 10, true);
                }
                else {
                    System.out.println("just visiting, pay nothing");
                }
            }
        }
    }

    private void payRR2(int rrTile) {
        this.user.setTile(rrTile);
        view.movePlayer(this.user,rrTile);
        for (Property p : properties) {
            if (p.getBoardIndex() == rrTile) {
                p.action();
            }
        }
    }

}
