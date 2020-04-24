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

    public GoToJail(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.setJailed();
        this.visiting = null;
    }
}
