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

    public Go(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.receive(GO_MONEY);
    }
}
