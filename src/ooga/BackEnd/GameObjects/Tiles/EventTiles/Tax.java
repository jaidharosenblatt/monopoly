package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public class Tax extends Event {

    private int tax_cost;

    public Tax() {}

    public Tax(String tileID, int boardIndex, int tax_cost) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.tax_cost = tax_cost;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.payBank(this.tax_cost);
    }
}
