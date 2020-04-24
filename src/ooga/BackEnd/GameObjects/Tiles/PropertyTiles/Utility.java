package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

/**
 * @author rodrigoaraujo A Utility tile. Extends Property and adds unique features
 * only available to utilities, such as rent calculation.
 */

public class Utility extends Property {

    private String pathname;

    /**
     * default constructor for parsing
     */

    public Utility() {}

    /**
     * Creates the utility class (subclass of Property). Most variables get established
     * after parsing and throughout the game.
     *
     * @param tileID String value of number gotten from XML
     * @param boardIndex int value from 0-39
     */

    public Utility(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.owner = null;
        this.cost = 150;
        this.group_color = "utils";
        this.group_number = 2;
        this.mortgaged = false;
    }

    /**
     * Calculates rent cost based on dice rolls and how many utility
     * properties are owned.
     *
     * @return int
     */

    @Override
    public int getRent() {
        if (this.owner.hasMonopoly(this)) {
            return (this.visiting.dice1 + this.visiting.dice2) * 10;
        }
        else {
            return (this.visiting.dice1 + this.visiting.dice2) * 4;
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
