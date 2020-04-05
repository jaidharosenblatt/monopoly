package ooga.usecases.BackEnd.Purchasables;

import ooga.usecases.BackEnd.Purchasables.Purchasable;

public class RailRoad extends Purchasable {

    private static final int RENT = 25;

    public RailRoad(int tileID) {
        this.tileID = tileID;
        this.owner = null;
        this.cost = 0;
        this.group_color = "rr";
        this.group_number = 4;
        this.mortgaged = false;
    }

    public int getRent(int dice1, int dice2) {
        int total = 0;
        for (Purchasable P : this.owner.getProperties()) {
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
}
