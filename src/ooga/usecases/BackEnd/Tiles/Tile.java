package ooga.usecases.BackEnd.Tiles;

import ooga.usecases.BackEnd.Player;

public abstract class Tile {
    protected int boardIndex;
    protected int tileID;
    protected Player visiting;

    public abstract void action();

    public int getBoardIndex() {return this.boardIndex;}

    public void onTile(Player P) {this.visiting = P;}
}
