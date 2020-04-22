package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

public class Utility extends Property {

    private String pathname;

    public Utility() {}

    public Utility(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.owner = null;
        this.cost = 150;
        this.group_color = "utils";
        this.group_number = 2;
        this.mortgaged = false;
    }

    @Override
    public int getRent() {
        if (this.owner.hasMonopoly(this)) {
            return (this.visiting.dice1 + this.visiting.dice2) * 10;
        }
        else {
            return (this.visiting.dice1 + this.visiting.dice2) * 4;
        }
    }

    @Override
    public void bankrupt() {
        this.mortgaged = false;
    }

    public void setPathname(String s) {this.pathname = s;}

    public String getPathname() {return this.pathname;}
}
