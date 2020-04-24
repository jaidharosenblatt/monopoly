package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class cardTile extends Event {

    private String type;
    private List<Property> properties;
    private List<Player> players;

    public cardTile() {}

    public cardTile(String tileID, int boardIndex, String type) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.type = type;
        this.visiting = null;
        properties = new ArrayList<>();
        players = new ArrayList<>();
    }

    @Override
    public void action() {this.visiting.drawCard(this.type, properties, players);}

    public void updateProps(List<Property> properties) { this.properties = properties; }

    public void playerList(List<Player> players) {
        this.players = players;
    }

    public void setType(String type) {this.type = type;}

    public String getType() {return this.type;}
}
