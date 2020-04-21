package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameLogic.Decision;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static final List<String> option = List.of("OK");

    private View view;
    private Player user;
    private String type;
    private ArrayList<Property> properties;
    private ArrayList<Player> players;

    public Cards(String type, Player user, ArrayList<Property> properties, ArrayList<Player> players, View view) {
        this.type = type;
        this.user = user;
        this.properties = properties;
        this.players = players;
        this.view = view;
    }

    public void action() {
        Decision d = new Decision(user.getName() + " just pulled a " + this.type + " card", option);
        view.makeUserDecision(d);
        if (this.type.equals("Chance")) {
            int probality = (int) (Math.random() * 16) + 1;
            switch (probality) {
                case 1:
                    Decision d1 = new Decision("Advance to \"GO\". (Collect $200)", option);
                    view.makeUserDecision(d1);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
                    Decision d2 = new Decision("Advance to Illinois Avenue. If you pass Go, collect $200.", option);
                    view.makeUserDecision(d2);
                    if (user.getTile() > ILLINOIS_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(ILLINOIS_INDEX);
                    for (Property p : properties) {
                        if (p.getBoardIndex() == ILLINOIS_INDEX) {
                            p.action();
                        }
                    }
                    break;
                case 3:
                    Decision d3 = new Decision("Advance to St. Charles Place. If you pass Go, collect $200.", option);
                    view.makeUserDecision(d3);
                    if (user.getTile() > ST_CHARLES_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(ST_CHARLES_INDEX);
                    for (Property p : properties) {
                        if (p.getBoardIndex() == ST_CHARLES_INDEX) {
                            p.action();
                        }
                    }
                    break;
                case 4:
                    Decision d4 = new Decision("Advance token to nearest Utility. If unowned, you may buy it from the Bank. " +
                                                       "If owned, throw dice and pay owner a total 10 times the amount thrown.", option);
                    view.makeUserDecision(d4);
                    if (user.getTile() > 20) {
                        payUtil10(WATER_INDEX);
                    }
                    else {
                        payUtil10(ELECTRIC_INDEX);
                    }
                    break;
                case 5:
                case 6:
                    Decision d5 = new Decision("Advance token to the nearest Railroad and pay owner twice the rental to which " +
                                                      "he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", option);
                    view.makeUserDecision(d5);
                    if (user.getTile() < 10) {
                        payRR2(READING_RR_INDEX);
                    }
                    else if (user.getTile() < 30) {
                        payRR2(BO_RR_INDEX);
                    }
                    else {
                        payRR2(SHORT_RR_INDEX);
                    }
                    break;
                case 7:
                    Decision d6 = new Decision("Bank pays you dividend of $50.", option);
                    view.makeUserDecision(d6);
                    user.receive(50);
                    break;
                case 8:
                    Decision d7 = new Decision("Get out of Jail Free. This card may be kept until needed, or traded/sold.", option);
                    view.makeUserDecision(d7);
                    user.getJailFreeCard();
                    break;
                case 9:
                    Decision d8 = new Decision("Go Back Three Spaces.", option);
                    view.makeUserDecision(d8);
                    int newTile = this.user.getTile() - 3;
                    this.user.moveTo(newTile);
                    break;
                case 10:
                    Decision d9 = new Decision("Go to Jail. Go directly to Jail. Do not pass GO, do not collect $200.", option);
                    view.makeUserDecision(d9);
                    user.setJailed();
                    break;
                case 11:
                    Decision d10 = new Decision( "Make general repairs on all your property: For each house pay $25, For each hotel $125.", option);
                    view.makeUserDecision(d10);
                    user.payBank(user.getHouses() * 25);
                    break;
                case 12:
                    Decision d11 = new Decision( "Pay poor tax of $15", option);
                    view.makeUserDecision(d11);
                    user.payBank(15);
                    break;
                case 13:
                    Decision d12 = new Decision( "Take a trip to Reading Railroad. If you pass Go, collect $200.", option);
                    view.makeUserDecision(d12);
                    if (user.getTile() > READING_RR_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(READING_RR_INDEX);
                    break;
                case 14:
                    Decision d13 = new Decision( "Take a walk on the Boardwalk. Advance token to Boardwalk.", option);
                    view.makeUserDecision(d13);
                    user.moveTo(BOARDWALK_INDEX);
                    break;
                case 15:
                    Decision d14 = new Decision( "You have been elected Chairman of the Board. Pay each player $50.", option);
                    view.makeUserDecision(d14);
                    for (Player p : players) {
                        if (user != p) {
                            user.payPlayer(p, 50);
                        }
                    }
                    break;
                case 16:
                    Decision d15 = new Decision( "Your building loan matures. Receive $150.", option);
                    view.makeUserDecision(d15);
                    user.receive(150);
                    break;
                case 17:
                    Decision d16 = new Decision( "You have won a crossword competition. Collect $100.", option);
                    view.makeUserDecision(d16);
                    user.receive(100);
                    break;
            }
        }
        if (this.type.equals("Community")) {
            int probality = (int) (Math.random() * 17) + 1;
            switch (probality) {
                case 1:
                    Decision d1 = new Decision("Advance to \"GO\". (Collect $200)", option);
                    view.makeUserDecision(d1);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
                    Decision d2 = new Decision("Bank error in your favor. Collect $200.", option);
                    view.makeUserDecision(d2);
                    user.receive(CROSS_GO);
                    break;
                case 3:
                    Decision d3 = new Decision("Doctor's fees. Pay $50.", option);
                    view.makeUserDecision(d3);
                    user.payBank(50);
                    break;
                case 4:
                    Decision d4 = new Decision("From sale of stock you get $50.", option);
                    view.makeUserDecision(d4);
                    user.receive(50);
                    break;
                case 5:
                    Decision d5 = new Decision("Get Out of Jail Free. This card may be kept until needed or sold/traded.", option);
                    view.makeUserDecision(d5);
                    user.getJailFreeCard();
                    break;
                case 6:
                    Decision d6 = new Decision("Go to Jail. Go directly to jail. Do not pass Go, Do not collect $200.", option);
                    view.makeUserDecision(d6);
                    user.setJailed();
                    break;
                case 7:
                    Decision d7 = new Decision("Grand Opera Night. Collect $50 from every player for opening night seats.", option);
                    view.makeUserDecision(d7);
                    for (Player p : players) {
                        if (user != p) {
                            p.payPlayer(user, 50);
                        }
                    }
                    break;
                case 8:
                    Decision d8 = new Decision("Holiday Fund matures. Receive $100.", option);
                    view.makeUserDecision(d8);
                    user.receive(100);
                    break;
                case 9:
                    Decision d9 = new Decision("Income tax refund. Collect $20.", option);
                    view.makeUserDecision(d9);
                    user.receive(20);
                    break;
                case 10:
                    Decision d10 = new Decision("It is your birthday. Collect $10 from every player.", option);
                    view.makeUserDecision(d10);
                    for (Player p : players) {
                        if (user != p) {
                            p.payPlayer(user, 10);
                        }
                    }
                    break;
                case 11:
                    Decision d11 = new Decision("Life insurance matures. Collect $100", option);
                    view.makeUserDecision(d11);
                    user.receive(100);
                    break;
                case 12:
                    Decision d12 = new Decision("Hospital Fees. Pay $50.", option);
                    view.makeUserDecision(d12);
                    user.payBank(50);
                    break;
                case 13:
                    Decision d13 = new Decision("School Fees. Pay $50.", option);
                    view.makeUserDecision(d13);
                    user.payBank(50);
                    break;
                case 14:
                    Decision d14 = new Decision("Receive $25 consultancy fee.", option);
                    view.makeUserDecision(d14);
                    user.receive(25);
                    break;
                case 15:
                    Decision d15 = new Decision("You are assessed for street repairs: Pay $40 per house and $200 per hotel you own.", option);
                    view.makeUserDecision(d15);
                    user.payBank(user.getHouses() * 40);
                    break;
                case 16:
                    Decision d16 = new Decision("You have won second prize in a beauty contest. Collect $10.", option);
                    view.makeUserDecision(d16);
                    user.receive(10);
                    break;
                case 17:
                    Decision d17 = new Decision("You inherit $100.", option);
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
                    List<String> option1 = List.of("Yes", "No");
                    Decision d = new Decision("Would you like to buy " + p.getTitle() + " for $" + p.getCost() + "?", option1);
                    view.makeUserDecision(d);
                    if (d.getChoice().equals("Yes")) {
                        if (this.user.getBalance() >= p.getCost()) {
                            p.setOwner(this.user);
                            this.user.buyProperty(p);
                        }
                    }
                }
                else if (this.user != p.getOwner() && !p.isMortgaged()) {
                    this.user.rollDice();
                    this.user.payPlayer(p.getOwner(), (this.user.dice1 + this.user.dice2) * 10);
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
                if (p.getOwner() != this.user && p.isOwned()) {
                    this.user.payPlayer(p.getOwner(), p.getRent());
                }
            }
        }
    }

}
