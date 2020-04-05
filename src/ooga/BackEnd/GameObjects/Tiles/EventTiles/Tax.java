package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public class Tax extends Tile {

    private int tax_cost;

    public Tax(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.tax_cost = 0;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.payBank(this.tax_cost);
    }
}
