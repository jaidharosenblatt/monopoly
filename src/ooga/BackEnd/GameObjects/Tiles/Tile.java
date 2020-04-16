package ooga.BackEnd.GameObjects.Tiles;

import ooga.BackEnd.GameObjects.Player;

public abstract class Tile {
    protected int boardIndex;
    protected String tileID;
    protected Player visiting;

    public abstract void action();

    //their is probably no need for tiles to know their own index and should probably have a super class the holds and indexes

    public int getBoardIndex() {return this.boardIndex;}

    public void onTile(Player P) {this.visiting = P;}

    public void setBoardIndex(int boardIndex) {this.boardIndex = boardIndex;}

    public void setTileID(String tileID) {this.tileID = tileID;}
}
