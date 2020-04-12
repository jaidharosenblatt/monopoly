package ooga.BackEnd.GameObjects.Tiles;

import ooga.BackEnd.GameObjects.Player;

public abstract class Tile {
    protected int boardIndex;
    protected int tileID;
    protected Player visiting;

    public abstract void action();

    public int getBoardIndex() {return this.boardIndex;}

    public void onTile(Player P) {this.visiting = P;}

    public void setBoardIndex(int boardIndex) {this.boardIndex = boardIndex;}

    public void setTileID(int tileID) {this.tileID = tileID;}
}
