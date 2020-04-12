package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public class GoToJail extends Tile {

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
