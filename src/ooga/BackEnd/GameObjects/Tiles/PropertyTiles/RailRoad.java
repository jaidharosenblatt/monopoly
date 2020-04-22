package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

public class RailRoad extends Property {

    private static final int RENT = 25;
    private String pathname;

    public RailRoad() {}

    public RailRoad(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.owner = null;
        this.cost = 200;
        this.group_color = "rr";
        this.group_number = 4;
        this.mortgaged = false;
    }

    @Override
    public int getRent() {
        int total = 0;
        for (Property P : this.owner.getProperties()) {
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

    public void setPathname(String s) {this.pathname = s;}

    public String getPathname() {return this.pathname;}
}
