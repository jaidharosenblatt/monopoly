package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.ArrayList;

public class Cards {

    private static final int JAIL_INDEX = 10;
    private static final int GO_INDEX = 0;
    private static final int ILLINOIS_INDEX = 24;
    private static final int ST_CHARLES_INDEX = 11;

    private static final int CROSS_GO = 200;

    private Player user;
    private String type;
    private ArrayList<Property> properties = new ArrayList<Property>();

    public Cards(String type, Player user, ArrayList<Property> properties) {
        this.type = type;
        this.user = user;
        this.properties = properties;
    }

    public void action() {
        if (this.type.equals("Chance")) {
            int probality = (int) (Math.random() * 16) + 1;
            switch (probality) {
                case 1:
                    System.out.println("Advance to \"GO\". (Collect $200)");
                    user.receive(CROSS_GO);
                    user.moveTo(GO_INDEX);
                    break;
                case 2:
                    System.out.println("Advance to Illinois Avenue. If you pass Go, collect $200.");
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
                    System.out.println("Advance to St. Charles Place. If you pass Go, collect $200.");
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
                    System.out.println("Advance token to nearest Utility. If unowned, you may buy it from the Bank. " +
                                       "If owned, throw dice and pay owner a total 10 times the amount thrown.");

                    break;
                case 5:
                    System.out.println("Advance token to the nearest Railroad and pay owner twice the rental to which " +
                                       "he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.");

                    break;
                case 6:
                    System.out.println("Advance token to the nearest Railroad and pay owner twice the rental to which " +
                            "he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.");

                    break;
                case 7:
                    System.out.println("Bank pays you dividend of $50.");

                    break;
                case 8:
                    System.out.println("Get out of Jail Free. This card may be kept until needed, or traded/sold.");

                    break;
                case 9:
                    System.out.println("Go Back Three Spaces.");

                    break;
                case 10:
                    System.out.println("Go to Jail. Go directly to Jail. Do not pass GO, do not collect $200.");

                    break;
                case 11:
                    System.out.println("Make general repairs on all your property: For each house pay $25, For each hotel $100.");

                    break;
                case 12:
                    System.out.println("Pay poor tax of $15");

                    break;
                case 13:
                    System.out.println("Take a trip to Reading Railroad. If you pass Go, collect $200.");

                    break;
                case 14:
                    System.out.println("Take a walk on the Boardwalk. Advance token to Boardwalk.");

                    break;
                case 15:
                    System.out.println("You have been elected Chairman of the Board. Pay each player $50.");

                    break;
                case 16:
                    System.out.println("Your building loan matures. Receive $150.");

                    break;
                case 17:
                    System.out.println("You have won a crossword competition. Collect $100.");

                    break;
            }
        }
        if (this.type.equals("Community")) {
            int probality = (int) (Math.random() * 17) + 1;
            switch (probality) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
            }
        }
    }
}
