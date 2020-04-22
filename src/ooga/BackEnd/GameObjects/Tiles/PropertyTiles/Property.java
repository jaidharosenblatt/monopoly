package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

import java.util.List;

import javafx.scene.paint.Color;
import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.view.board.PropertyView;

public abstract class Property extends Tile {

    private final static int TILE_WIDTH = 60;
    private final static int TILE_HEIGHT = 60;

    protected String title_deed;
    protected Player owner;
    protected int cost;
    protected String group_color;
    protected int group_number;
    protected boolean mortgaged;

    @Override
    public void action() {
        System.out.println(this.visiting.getName() + " just landed on " + this.title_deed);
        if (!isOwned()) {

            List<String> options = List.of("Yes","No");
            Decision d = new Decision("Would you like to buy " + this.title_deed + " for $" + this.cost + "?",options);
            getView().makeUserDecision(d);

          if (d.getChoice().equals("Yes")) {
            if (this.visiting.getBalance() >= this.cost) {
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

    public abstract int getRent();

    public void setTitle(String title) {this.title_deed = title;}

    public String getTitle() {return this.title_deed;}

    public String getPropID() {return tileID;}

    public boolean isOwned() {return (owner == null) ? false : true;}

    public void setOwner(Player P) {this.owner = P;}

    public Player getOwner() {return this.owner;}

    public int getCost() {return this.cost;}

    public void setCost(int cost) {this.cost = cost;}

    public String getGroupColor() {return this.group_color;}

    public void setGroupColor(String groupColor) {this.group_color = groupColor;}

    public int getGroupNumber() {return this.group_number;}

    public void setGroupNumber(int groupNumber) {this.group_number = groupNumber;}

    public boolean isMortgaged() {return this.mortgaged;}

    public void setMortgaged() {
        System.out.println(this.owner + " just mortgaged " + this.title_deed);
        this.mortgaged = true;
        this.owner.receive((this.cost/2));
    }

    public void liftMortgage() {
        System.out.println(this.owner + " just paid " + this.cost * 1.1 + " to unmortgage " + this.title_deed);
        this.mortgaged = false;
        this.owner.payBank((int) (this.cost * 1.1), false);
    }

    public PropertyView convertView() {
        return new PropertyView(this.title_deed, this.cost, Color.web(group_color), TILE_WIDTH, TILE_HEIGHT);
    }
}
