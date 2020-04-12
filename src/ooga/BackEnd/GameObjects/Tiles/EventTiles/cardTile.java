package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.ArrayList;

public class cardTile extends Tile {

    private String type;
    private ArrayList<Property> properties = new ArrayList<Property>();
    private ArrayList<Player> players = new ArrayList<Player>();

    public cardTile(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
        this.type = "chance";
    }

    @Override
    public void action() {
        this.visiting.drawCard(this.type, properties, players);
    }

    public void updateProps(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void playerList(ArrayList<Player> players) {
        this.players = players;
    }
}
