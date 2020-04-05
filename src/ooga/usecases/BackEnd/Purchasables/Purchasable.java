package ooga.usecases.BackEnd.Purchasables;

import ooga.usecases.BackEnd.Player;

public class Purchasable {

    protected Player owner;
    protected int propID;
    protected int cost;
    protected String group_color;
    protected int group_number;
    protected boolean mortgaged;

    public int getPropID() {return propID;}

    public boolean isOwned() {return (owner == null) ? false : true;}

    public void setOwner(Player P) {this.owner = P;}

    public int getCost() {return this.cost;}

    public String getGroupColor() {return this.group_color;}

    public int getGroupNumber() {return this.group_number;}

    public boolean isMortgaged() {return this.mortgaged;}

    public void setMortgaged() {
        this.mortgaged = true;
        this.owner.receive((this.cost/2));
    }

    public void liftMortgage() {
        this.mortgaged = false;
        this.owner.payBank((int) (this.cost * 1.1));
    }
}
