package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public class Go extends Tile {

    private static final int GO_MONEY = 200;

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
