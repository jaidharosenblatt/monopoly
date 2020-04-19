package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.cardTile;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.RailRoad;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Utility;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGame {

    private ArrayList<Property> properties;
    private ArrayList<Street> streets;
    private ArrayList<RailRoad> railroads;
    private ArrayList<Utility> utilities;
    private ArrayList<Event> eventTiles;
    private ArrayList<Tile> allTiles;
    private ArrayList<Player> activePlayers;
    Player[] players;

    public LoadGame(String game_pathname, int player_number) throws FileNotFoundException, XMLStreamException {
        XMLParser parse = new XMLParser(game_pathname);

        this.properties = (ArrayList<Property>) parse.properties.clone();
        this.streets = (ArrayList<Street>) parse.streets.clone();
        this.utilities = (ArrayList<Utility>) parse.utilities.clone();
        this.railroads = (ArrayList<RailRoad>) parse.railroads.clone();
        this.eventTiles = (ArrayList<Event>) parse.eventTiles.clone();
        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();

        createPlayers(player_number);

        int t = 0;
        String input = "";
        while(t != 4) {
            for (Player p : this.players) {
                updateCardTiles();
                displayAssets(p);
                System.out.println("");
                promptPlayer(p);
                basicTurn(p);
                System.out.println("");
                input = "";
                while(!input.equals("end")) {
                    input = decision(p);
                }
            }
            t++;
        }
    }

    private void createPlayers(int player_number) {
        this.activePlayers = new ArrayList<>();
        this.players = new Player[player_number];
        Player[] temp = new Player[player_number];
        for (int i = 0; i < player_number; i++) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = myObj.nextLine();
            temp[i] = new Player(name, this.allTiles);
            activePlayers.add(temp[i]);
        }
        this.players = rollForOrder(temp);
        updateCardTiles();
    }

    private void updateCardTiles() {
        for (Event e : this.eventTiles) {
            if (e instanceof cardTile) {
                ((cardTile) e).playerList(this.activePlayers);
                ((cardTile) e).updateProps(this.properties);
            }
        }
    }

    private Player[] rollForOrder(Player[] list) {
        Player[] order = new Player[list.length];
        int counter = 0;
        ArrayList<Integer> chosen = new ArrayList<>();
        while (chosen.size() < 4) {
            int probality = (int) (Math.random() * list.length);
            if (!chosen.contains(probality)) {
                chosen.add(probality);
                order[counter] = list[probality];
                counter++;
            }
        }
        return order;
    }

    private void displayAssets(Player p) {
        System.out.println("---------------------------------");
        System.out.println(p.getName() + " has $" + p.getBalance());
        System.out.print(p.getName() + " owns:");
        for (Property prop : p.getProperties()) {
            System.out.print(" " + prop.getTitle() + " ");
        }
        System.out.println("");
        System.out.println(p.getName() + " is on " + p.getTileName());
        System.out.println("---------------------------------");
    }

    private void basicTurn(Player p) {
        if (this.activePlayers.contains(p)) {
            rollDice(p);
            if (p.dice1 == p.dice2) {
                System.out.println(p.getName() + " has rolled doubles! Roll again.");
                rollDice(p);
                if (p.dice1 == p.dice2) {
                    System.out.println(p.getName() + " was caught speeding! Go to Jail.");
                    p.setJailed();
                }
            }
        }
    }

    private void rollDice(Player p) {
        p.rollDice();
        int new_tile = p.getTile() + p.dice1 + p.dice2;
        if (new_tile > 39) {new_tile -= 40;}
        p.moveTo(new_tile);
    }

    private String decision(Player p) {
        Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
        System.out.println("Would you like to trade, build houses, mortgage property, or end your turn? [trade, build, sell, mortgage, end]: ");
        String input = myObj.nextLine();
        if (input.equals("cheat")) {
            p.setProperties(this.properties);
            for (Property t : this.properties) {
                t.setOwner(p);
            }
            return "";
        }
        if (input.equals("trade")) {
            System.out.println("Which player would you like to trade with?");
        }
        if (input.equals("build")) {
            if (p.getProperties().size() < 1) {
                System.out.println("You do not have any properties to build houses with");
                return "";
            }
            int check = 0;
            for (Property owned : p.getProperties()) {
                if (p.hasMonopoly(owned)) {
                    check++;
                    if (owned instanceof Street) {
                        if (p.getBalance() < ((Street) owned).getHouseCost()) {
                            System.out.println("Not enough funds");
                            return "";
                        }
                    }
                }
            }
            if (check < 1) {
                System.out.println("You do not have a monopoly of any property");
                return "";
            }
            buildLoop(p);
            return "";
        }
        if (input.equals("mortgage")) {
            System.out.println("Which property would you like to mortgage?");
        }
        return input;
    }

    private void buildLoop(Player p) {
        String test = "";
        while(!test.equals("done")) {
            Scanner myObj2 = new Scanner(System.in); //replace this with front-end decision instead
            System.out.println("What property would you like to build a house on?");
            String input2 = myObj2.nextLine();
            ArrayList<Street> monopoly_set = new ArrayList<>();
            loop:
            for (Property owned : p.getProperties()) {
                if (owned.getTitle().equals(input2) && (owned instanceof Street)) {
                    monopoly_set.add((Street) owned);
                    for (Property q : p.getProperties()) {
                        if (owned.getGroupColor().equals(q.getGroupColor()) && (q instanceof Street)) {
                            monopoly_set.add((Street) q);
                        }
                    }
                    for (Street r : monopoly_set) {
                        int diff = ((Street) owned).getHouses() - r.getHouses();
                        if (diff > 0) {
                            System.out.println("Choose another property, must keep house number even");
                            break loop;
                        }
                    }
                    p.buyHouse(1, (Street) owned);
                    test = "done";
                    break loop;
                }
            }
        }
    }
    private void promptPlayer(Player p) {
        String input = "";
        System.out.println("Would you like to do anything before rolling? [Y or N]");
        Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
        String decision = myObj.nextLine();
        if (decision.equals("Y")) {
            while(!input.equals("end")) {
                input = decision(p);
            }
        }
    }
}
