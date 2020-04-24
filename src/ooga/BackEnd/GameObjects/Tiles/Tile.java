package ooga.BackEnd.GameObjects.Tiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.view.View;

/**
 * @author rodrigoaraujo the superclass of Property and Event. A Tile is a generic
 * class to unite all of the different types of tiles present on the board, allowing
 * for one list of all tiles to exist.
 */

public abstract class Tile {
    protected View view;
    protected int boardIndex;
    protected String tileID;
    protected Player visiting;

    /**
     * every Tile has an action command that updates the visiting player's
     * information based on what that tile does. Property tiles give players
     * the option of buying or force them to pay the owner. Event tiles can
     * give players money or allow them to draw cards.
     */

    public abstract void action();

    /**
     * Get view
     *
     * @return View
     */

    public View getView() {return view;}

    /**
     * Set view
     *
     */

    public void setView(View view) {this.view = view;}

    /**
     * Get boardIndex
     *
     * @return int
     */

    public int getBoardIndex() {return this.boardIndex;}

    /**
     * Sets player as the visiter to the tile
     *
     * @param P player visiting
     */

    public void onTile(Player P) {this.visiting = P;}

    /**
     * Sets boardIndex for the tile
     *
     * @param boardIndex integer value
     */

    public void setBoardIndex(int boardIndex) {this.boardIndex = boardIndex;}

    /**
     * Sets tileID, used in parser
     *
     * @param tileID String value of a number
     */

    public void setTileID(String tileID) {this.tileID = tileID;}
}
