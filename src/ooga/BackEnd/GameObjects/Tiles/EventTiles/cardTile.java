package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.ArrayList;

public class cardTile extends Event {

    private String type;
    private ArrayList<Property> properties = new ArrayList<Property>();
    private ArrayList<Player> players = new ArrayList<Player>();

    public cardTile() {}

    public cardTile(String tileID, int boardIndex, String type) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.type = type;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.drawCard(this.type, properties, players);
    }

    public void updateProps(ArrayList<Property> properties) { this.properties = properties; }

    public void playerList(ArrayList<Player> players) {
        this.players = players;
    }

    public void setType(String type) {this.type = type;}

    public String getType() {return this.type;}
}
