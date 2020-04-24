package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

/**
 * @author rodrigoaraujo A RailRoad tile. Extends Property and adds unique features
 * only available to railroads, such as rent calculation.
 */

public class RailRoad extends Property {

    private static final int RENT = 25;
    private String pathname;

    /**
     * default constructor for parsing
     */

    public RailRoad() {}

    /**
     * Creates the railroad class (subclass of Property). Most variables get established
     * after parsing and throughout the game.
     *
     * @param tileID String value of number gotten from XML
     * @param boardIndex int value from 0-39
     */

    public RailRoad(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.owner = null;
        this.cost = 200;
        this.group_color = "rr";
        this.group_number = 4;
        this.mortgaged = false;
    }

    /**
     * Calculates rent cost based on how many railroad
     * properties are owned.
     *
     * @return int
     */

    @Override
    public int getRent() {
        int total = 0;
        for (Property P : this.owner.getProperties()) {
            if (P.group_color.equals(this.group_color)) {
                total++;
            }
        }
        if (total == 2) {
            return RENT * 2;
        }
        else if (total == 3) {
            return RENT * 3;
        }
        else if (total == 4) {
            return RENT * 4;
        }
        else {
            return RENT;
        }
    }

    /**
     * Resets mortgaged to false after owner goes bankrupt
     */

    @Override
    public void bankrupt() {
        this.mortgaged = false;
    }

    /**
     * Sets url for view to display
     *
     * @param s String value of url i.e. "railroad.gif"
     */

    public void setPathname(String s) {this.pathname = s;}

    /**
     * Gets the url of the pathname
     *
     * @return String
     */

    public String getPathname() {return this.pathname;}
}
