package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public class cardTile extends Tile {

    private String type;

    public cardTile(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
        this.type = "chance";
    }

    @Override
    public void action() {

    }
}
