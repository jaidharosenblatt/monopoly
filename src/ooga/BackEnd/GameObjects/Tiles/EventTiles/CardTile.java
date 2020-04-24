package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigoaraujo Event tile for cards
 */

public class CardTile extends Event {

    private String type;
    private List<Property> properties;
    private List<Player> players;

    /**
     * default constructor for parsing
     */

    public CardTile() {}

    /**
     * Creates CardTile class, which is the subclass to Event,
     * which is the subclass to Tile.
     *
     * @param tileID String value of number gotten from XML
     * @param boardIndex int value from 0-39
     * @param type String value of either "Chance" or "Community"
     */

    public CardTile(String tileID, int boardIndex, String type) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.type = type;
        this.visiting = null;
        properties = new ArrayList<>();
        players = new ArrayList<>();
    }

    /**
     * Makes visiting player draw a card of this tile's type
     */

    @Override
    public void action() {this.visiting.drawCard(this.type, properties, players);}

    /**
     * Updates list of all properties
     *
     * @param properties list of all properties
     */

    public void updateProps(List<Property> properties) { this.properties = properties; }

    /**
     * Updates list of active players
     *
     * @param players list of all players in the game
     */

    public void updatePlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Sets the type of card drawn (taken from parser)
     *
     * @param type String value of either "Chance" or "Community"
     */

    public void setType(String type) {this.type = type;}

    /**
     * Gets the type of card this tile contains
     *
     * @return String
     */

    public String getType() {return this.type;}
}
