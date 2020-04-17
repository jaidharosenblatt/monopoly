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
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGame {

    private ArrayList<Property> properties;
    private ArrayList<Street> streets;
    private ArrayList<RailRoad> railroads;
    private ArrayList<Utility> utilities;
    private ArrayList<Event> eventTiles;
    private ArrayList<Tile> allTiles;

    public LoadGame(String game_pathname, int player_number) throws FileNotFoundException, XMLStreamException {
        XMLParser parse = new XMLParser(game_pathname);

        this.properties = (ArrayList<Property>) parse.properties.clone();
        this.streets = (ArrayList<Street>) parse.streets.clone();
        this.utilities = (ArrayList<Utility>) parse.utilities.clone();
        this.railroads = (ArrayList<RailRoad>) parse.railroads.clone();
        this.eventTiles = (ArrayList<Event>) parse.eventTiles.clone();
        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();

        Player[] players = new Player[player_number];
        for (int i = 0; i < player_number; i++) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = myObj.nextLine();
            players[i] = new Player(name, this.allTiles);
        }
    }
}
