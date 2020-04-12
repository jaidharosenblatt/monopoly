package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

public class Utility extends Property {

    public Utility(int tileID, int boardIndex) {
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
}
