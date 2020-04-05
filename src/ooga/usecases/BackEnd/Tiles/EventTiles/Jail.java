package ooga.usecases.BackEnd.Tiles.EventTiles;

import ooga.usecases.BackEnd.Tiles.Tile;

public class Jail extends Tile {

    public Jail(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
    }

    @Override
    public void action() {

    }
}
