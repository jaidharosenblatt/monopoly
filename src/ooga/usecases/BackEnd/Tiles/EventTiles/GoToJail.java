package ooga.usecases.BackEnd.Tiles.EventTiles;

import ooga.usecases.BackEnd.Tiles.Tile;

public class GoToJail extends Tile {

    public GoToJail(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.setJailed();
        this.visiting = null;
    }
}
