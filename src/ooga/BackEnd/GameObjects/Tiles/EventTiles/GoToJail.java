package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

/**
 * @author rodrigoaraujo Event tile for Go To Jail
 */

public class GoToJail extends Event {

    /**
     * default constructor for parsing
     */

    public GoToJail() {}

    /**
     * Creates GoToJail class, which is the subclass to Event,
     * which is the subclass to Tile.
     *
     * @param tileID String value of number gotten from XML
     * @param boardIndex int value from 0-39
     */

    public GoToJail(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    /**
     * Sets the player status to jailed (which moves the player to the Jail index)
     * and removes the player from visiting this tile
     */

    @Override
    public void action() {
        this.visiting.setJailed();
        this.visiting = null;
    }
}
