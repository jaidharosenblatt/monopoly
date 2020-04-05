package ooga.usecases.BackEnd.Tiles.Properties;

public class Utility extends Property {

    public Utility(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.owner = null;
        this.cost = 0;
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
}
