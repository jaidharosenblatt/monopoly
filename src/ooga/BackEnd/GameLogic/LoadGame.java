package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Player;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class LoadGame {

    public LoadGame(String game_pathname, int player_number) throws FileNotFoundException, XMLStreamException {
        XMLParser parse = new XMLParser(game_pathname);
        Player[] players = new Player[player_number];
        for (int i = 0; i < player_number; i++) {
            String name = "input here";
            players[i] = new Player(name, parse.allTiles);
        }
    }
}
