package ooga.BackEnd.GameObjects.Tiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.view.View;

public abstract class Tile {
    protected View view;
    protected int boardIndex;
    protected String tileID;
    protected Player visiting;

  public View getView() {
    return view;
  }

  public void setView(View view) {
    this.view = view;
  }

  public abstract void action();

    public int getBoardIndex() {return this.boardIndex;}

    public void onTile(Player P) {this.visiting = P;}

    public void setBoardIndex(int boardIndex) {this.boardIndex = boardIndex;}

    public void setTileID(String tileID) {this.tileID = tileID;}
}
