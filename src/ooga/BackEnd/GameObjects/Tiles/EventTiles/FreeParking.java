package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public class FreeParking extends Tile {

    public FreeParking(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        System.out.println("Some versions of the game allow for this spot to do something");
    }
}

