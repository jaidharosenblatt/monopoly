package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

/**
 * @author rodrigoaraujo Event tile for tax
 */

public class Tax extends Event {

    private int tax_cost;

    /**
     * default constructor for parsing
     */

    public Tax() {}

    /**
     * Creates event tile for Tax
     *
     * @param tileID unique string value parsed from XML
     * @param boardIndex int value for location on board (0-39)
     * @param tax_cost int value for tax cost
     */

    public Tax(String tileID, int boardIndex, int tax_cost) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.tax_cost = tax_cost;
        this.visiting = null;
    }

    @Override
    public void action() {
        this.visiting.payBank(this.tax_cost, true);
    }

    public void setTax(int tax) {this.tax_cost = tax;}

    public int getTax() {return this.tax_cost;}
}
