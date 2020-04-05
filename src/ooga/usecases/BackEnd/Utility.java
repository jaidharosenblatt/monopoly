package ooga.usecases.BackEnd;

public class Utility {
    private Player owner;
    private int propID;
    private int cost;
    private String group_color;
    private int group_number;
    private boolean mortgaged;

    public Utility(int propID) {
        this.owner = null;
        this.cost = 0;
        this.group_color = "utils";
        this.group_number = 2;
        this.mortgaged = false;
    }

    public int getRent(int dice1, int dice2) {
        if (true) { //temporary
            return (dice1 + dice2) * 10;
        }
        else {
            return (dice1 + dice2) * 4;
        }
    }

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
