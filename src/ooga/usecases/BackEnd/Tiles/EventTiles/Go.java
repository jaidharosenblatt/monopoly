package ooga.usecases.BackEnd.Tiles.EventTiles;

import ooga.usecases.BackEnd.Tiles.Tile;

public class Go extends Tile {

    private static final int GO_MONEY = 200;

    public Go(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.receive(GO_MONEY);
    }
}
