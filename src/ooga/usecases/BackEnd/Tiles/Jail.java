package ooga.usecases.BackEnd.Tiles;

public class Jail extends Tile {

    public Jail(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
    }

    @Override
    public void action() {

    }
}
