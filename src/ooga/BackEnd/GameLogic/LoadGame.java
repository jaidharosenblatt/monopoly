package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
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
    Player[] players;

    public LoadGame(String game_pathname, int player_number) throws FileNotFoundException, XMLStreamException {
        XMLParser parse = new XMLParser(game_pathname);

        this.properties = (ArrayList<Property>) parse.properties.clone();
        this.streets = (ArrayList<Street>) parse.streets.clone();
        this.utilities = (ArrayList<Utility>) parse.utilities.clone();
        this.railroads = (ArrayList<RailRoad>) parse.railroads.clone();
        this.eventTiles = (ArrayList<Event>) parse.eventTiles.clone();
        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();

        this.players = new Player[player_number];
        Player[] temp = new Player[player_number];
        for (int i = 0; i < player_number; i++) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = myObj.nextLine();
            temp[i] = new Player(name, this.allTiles);
        }
        this.players = rollForOrder(temp);

        while(true) {
            for (Player p : this.players) {
                p.rollDice();
                int new_tile = p.getTile() + p.dice1 + p.dice2;
                p.moveTo(new_tile);
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
}
