package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

/**
 * @author rodrigoaraujo Event tile for Go
 */

public class Go extends Event {

    private static final int GO_MONEY = 200;

    /**
     * default constructor for parsing
     */

    public Go() {}

    /**
     * Creates Go class, which is the subclass to Event,
     * which is the subclass to Tile.
     *
     * @param tileID String value of number gotten from XML
     * @param boardIndex int value from 0-39
     */

    public Go(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    /**
     * Gives player $200 for landing on it (passing Go is handled
     * inside of rollDiceAndMove from BackendExternal api method)
     */

    @Override
    public void action() {
        this.visiting.receive(GO_MONEY);
    }
}
