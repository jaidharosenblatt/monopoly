package ooga.usecases.BackEnd.Tiles;

public class GoToJail extends Tile{

    public GoToJail(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.visiting = null;
    }

    @Override
    public void action() {
        if (this.visiting != null) {
            this.visiting.setJailed();
        }
    }
}
