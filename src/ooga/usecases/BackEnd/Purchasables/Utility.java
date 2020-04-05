package ooga.usecases.BackEnd.Purchasables;

import ooga.usecases.BackEnd.Purchasables.Purchasable;

public class Utility extends Purchasable {

    public Utility(int tileID) {
        this.tileID = tileID;
        this.owner = null;
        this.cost = 0;
        this.group_color = "utils";
        this.group_number = 2;
        this.mortgaged = false;
    }

    public int getRent(int dice1, int dice2) {
        if (this.owner.hasMonopoly(this)) {
            return (dice1 + dice2) * 10;
        }
        else {
            return (dice1 + dice2) * 4;
        }
    }
}
