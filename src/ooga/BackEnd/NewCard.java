package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewCard {
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

    private Player user;
    private String type;
    private ArrayList<Property> properties = new ArrayList<Property>();
    private ArrayList<Player> players = new ArrayList<Player>();

    public NewCard(String type, Player user, ArrayList<Property> properties, ArrayList<Player> players) {
        this.type = type;
        this.user = user;
        this.properties = properties;
        this.players = players;
    }

    public void action() {
        if (this.type.equals("Chance")) {
            int probality = (int) (Math.random() * 16) + 1;
            switch (probality) {
                case 1:
                    user.receive(CROSS_GO);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
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
                    if (user.getTile() > 20) {
                        payUtil10(WATER_INDEX);
                    }
                    else {
                        payUtil10(ELECTRIC_INDEX);
                    }
                    break;
                case 5:
                case 6:
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
                    user.receive(50);
                    break;
                case 8:
                    user.getJailFreeCard();
                    break;
                case 9:
                    int newTile = this.user.getTile() - 3;
                    this.user.moveTo(newTile);
                    for (Property p : properties) {
                        if (p.getBoardIndex() == newTile) {
                            p.action();
                        }
                    }
                    break;
                case 10:
                    user.setJailed();
                    break;
                case 11:
                    user.payBank(user.getHouses() * 25);
                    break;
                case 12:
                    user.payBank(15);
                    break;
                case 13:
                    if (user.getTile() > READING_RR_INDEX) {
                        user.receive(CROSS_GO);
                    }
                    user.moveTo(READING_RR_INDEX);
                    for (Property p : properties) {
                        if (p.getBoardIndex() == READING_RR_INDEX) {
                            p.action();
                        }
                    }
                    break;
                case 14:
                    user.moveTo(BOARDWALK_INDEX);
                    for (Property p : properties) {
                        if (p.getBoardIndex() == BOARDWALK_INDEX) {
                            p.action();
                        }
                    }
                    break;
                case 15:
                    for (Player p : players) {
                        if (user != p) {
                            user.payPlayer(p, 50);
                        }
                    }
                    break;
                case 16:
                    user.receive(150);
                    break;
                case 17:
                    user.receive(100);
                    break;
            }
        }
        if (this.type.equals("Community")) {
            int probality = (int) (Math.random() * 17) + 1;
            switch (probality) {
                case 1:
                    user.receive(CROSS_GO);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
                    user.receive(CROSS_GO);
                    break;
                case 3:
                    user.payBank(50);
                    break;
                case 4:
                    user.receive(50);
                    break;
                case 5:
                    user.getJailFreeCard();
                    break;
                case 6:
                    user.setJailed();
                    break;
                case 7:
                    for (Player p : players) {
                        if (user != p) {
                            p.payPlayer(user, 50);
                        }
                    }
                    break;
                case 8:
                    user.receive(100);
                    break;
                case 9:
                    System.out.println("Income tax refund. Collect $20.");
                    user.receive(20);
                    break;
                case 10:
                    System.out.println("It is your birthday. Collect $10 from every player.");
                    for (Player p : players) {
                        if (user != p) {
                            p.payPlayer(user, 10);
                        }
                    }
                    break;
                case 11:
                    System.out.println("Life insurance matures. Collect $100");
                    user.receive(100);
                    break;
                case 12:
                    System.out.println("Hospital Fees. Pay $50.");
                    user.payBank(50);
                    break;
                case 13:
                    System.out.println("School Fees. Pay $50.");
                    user.payBank(50);
                    break;
                case 14:
                    System.out.println("Receive $25 consultancy fee.");
                    user.receive(25);
                    break;
                case 15:
                    System.out.println("You are assessed for street repairs: Pay $40 per house and $200 per hotel you own.");
                    user.payBank(user.getHouses() * 40);
                    break;
                case 16:
                    System.out.println("You have won second prize in a beauty contest. Collect $10.");
                    user.receive(10);
                    break;
                case 17:
                    System.out.println("You inherit $100.");
                    user.receive(100);
                    break;
            }
        }
    }

    private void payUtil10(int utilTile) {
        this.user.moveTo(utilTile);
        for (Property p : this.properties) {
            if (p.getBoardIndex() == utilTile) {
                if (!p.isOwned()) {
                    try (Scanner scanner = new Scanner(System.in)) { //replace this with front-end decision instead
                        System.out.print("Would you like to buy this? [Y or N]: ");
                        String input = scanner.nextLine();
                        if (input.equals("Y")) {
                            if (this.user.getBalance() >= p.getCost()) {
                                p.setOwner(this.user);
                                this.user.buyProperty(p);
                            }
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
        this.user.moveTo(rrTile);
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