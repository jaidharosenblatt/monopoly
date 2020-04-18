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
        while(t != 4) {
            for (Player p : this.players) {
                updateCardTiles();
                displayAssets(p);
                System.out.println("");
                basicTurn(p);
                System.out.println("");
                decision(p);

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

    private void decision(Player p) {
        System.out.println("Player should choose to mortgage/build/trade property here");
    }
}
