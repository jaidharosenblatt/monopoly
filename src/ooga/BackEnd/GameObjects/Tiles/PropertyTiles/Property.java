package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

import java.util.List;

import javafx.scene.paint.Color;
import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.util.PropertiesGetter;
import ooga.view.board.PropertyView;

/**
 * @author rodrigoaraujo Any property tile on the board (St. Charles, Water Works,
 * Short Line) Subclasses are Street, Utility and RailRoad.
 */

public abstract class Property extends Tile {

    private final static int TILE_WIDTH = 60;
    private final static int TILE_HEIGHT = 60;

    protected String title_deed;
    protected Player owner;
    protected int cost;
    protected String group_color;
    protected int group_number;
    protected boolean mortgaged;
    private List<String> options = List.of(PropertiesGetter.getPromptFromKey("Yes"),PropertiesGetter.getPromptFromKey("No"));

    /**
     * All subclasses of Property follow the same rules when a player
     * lands on them. If it is owned, visiting player must pay rent.
     * If not, visiting player can choose to buy (if they can afford it).
     */

    @Override
    public void action() {
        System.out.println(this.visiting.getName() + " just landed on " + this.title_deed);
        if (!isOwned()) {
            Decision d = new Decision("Would you like to buy " + this.title_deed + " for $" + this.cost + "?",options);
            getView().makeUserDecision(d);

          if (d.getChoice().equals(PropertiesGetter.getPromptFromKey("Yes"))) {
            if (this.visiting.getCashBalance() >= this.cost) {
              this.setOwner(this.visiting);
              this.owner.buyProperty(this);
            }
            else {
              System.out.println(this.visiting.getName() + " cannot afford it");
            }
          }
        }
        else if (this.visiting != this.owner && !isMortgaged()) {
            this.visiting.payPlayer(this.owner, this.getRent(), true);
        }
        else {
            System.out.println("just visiting");
        }
    }

    /**
     * Gets rent cost
     *
     * @return int value
     */

    public abstract int getRent();

    /**
     * When owner becomes bankrupt, Property attributes must be rest
     */

    public abstract void bankrupt();

    /**
     * Sets title of Property (done in parser)
     *
     * @param title String value i.e. "Boardwalk"
     */

    public void setTitle(String title) {this.title_deed = title;}

    /**
     * Gets title of property
     *
     * @return String value
     */

    public String getTitle() {return this.title_deed;}

    /**
     * Gets property id
     *
     * @return String value
     */

    public String getPropID() {return tileID;}

    /**
     * Checks if there is an owner
     *
     * @return boolean
     */

    public boolean isOwned() {return (owner == null) ? false : true;}

    /**
     * Sets Owner (used after purchasing successfully)
     *
     * @param P player who purchased this property
     */

    public void setOwner(Player P) {this.owner = P;}

    /**
     * Gets Owner (player value)
     *
     * @return Player
     */

    public Player getOwner() {return this.owner;}

    /**
     * Gets cost of purchasing property
     *
     * @return int
     */

    public int getCost() {return this.cost;}

    /**
     * Sets cost of purchasing property
     *
     * @param cost int value
     */

    public void setCost(int cost) {this.cost = cost;}

    /**
     * Gets string value of color set of property. Used to
     * determine if player has a monopoly of one color set.
     *
     * @return String value i.e. "#1778c2"
     */

    public String getGroupColor() {return this.group_color;}

    /**
     * Sets string value of color set of property (done in
     * parser)
     *
     * @param groupColor String value
     */

    public void setGroupColor(String groupColor) {this.group_color = groupColor;}

    /**
     * Gets the total number of properties in the color set
     *
     * @return int value i.e. 2 for Baltic Avenue
     */

    public int getGroupNumber() {return this.group_number;}

    /**
     * Sets the total number of properties in color set (done
     * in parser).
     *
     * @param groupNumber int
     */

    public void setGroupNumber(int groupNumber) {this.group_number = groupNumber;}

    /**
     * Checks if property is mortgaged
     *
     * @return boolean
     */

    public boolean isMortgaged() {return this.mortgaged;}

    /**
     * Sets the property to be mortgaged and gives player half
     * the cost of the property back.
     */

    public void setMortgaged() {
        System.out.println(this.owner + " just mortgaged " + this.title_deed);
        this.mortgaged = true;
        this.owner.receive((this.cost/2));
    }

    /**
     * Lifts the mortgage from the property and makes the player
     * pay 1.1 times the original cost of it.
     */

    public void liftMortgage() {
        System.out.println(this.owner + " just paid " + this.cost * 1.1 + " to unmortgage " + this.title_deed);
        this.mortgaged = false;
        this.owner.payBank((int) (this.cost * 1.1), false);
    }

    /**
     * Converts property class into a PropertyView for the view
     *
     * @return PropertyView
     */

    public PropertyView convertView() {
        return new PropertyView(this.title_deed, "M" + this.cost, Color.web(group_color), TILE_WIDTH, TILE_HEIGHT);
    }
}
