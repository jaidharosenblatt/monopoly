package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

/**
 * @author rodrigoaraujo Any event tile on the board (Chance, Community,
 * Go, Jail, Go To Jail, Free Parking, and Tax).
 */

public abstract class Event extends Tile {
    private String name;
    private String boardName;
    private String pathname;

    /**
     * Sets name of the tile (taken from XML) and used for
     * parsing other values
     *
     * @param name String value i.e. "free_parking"
     */

    public void setName(String name) {this.name = name;}

    /**
     * Gets name of the tile
     *
     * @return String value
     */

    public String getName() {return this.name;}

    /**
     * Sets the display name for the tile for the view
     *
     * @param name String value i.e. "Community Chest"
     */

    public void setBName(String name) {this.boardName = name;}

    /**
     * Gets display name for tile
     *
     * @return String value
     */

    public String getBName() {return this.boardName;}

    /**
     * Sets pathname url for tile image to be used on the view
     *
     * @param s String value i.e. "incometax.png"
     */

    public void setPathname(String s) {this.pathname = s;}

    /**
     * Gets pathname url for tile image
     *
     * @return String value
     */

    public String getPathname() {return this.pathname;}
}
