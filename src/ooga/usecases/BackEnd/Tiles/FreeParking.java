package ooga.usecases.BackEnd.Tiles;

public class FreeParking extends Tile {

    public FreeParking(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
    }

    @Override
    public void action() {
        System.out.println("Some versions of the game allow for this spot to do something");
    }
}

