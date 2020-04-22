package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public abstract class Event extends Tile {
    private String name;
    private String boardName;
    private String pathname;

    public void setName(String name) {this.name = name;}

    public String getName() {return this.name;}

    public void setBName(String name) {this.boardName = name;}

    public String getBName() {return this.boardName;}

    public void setPathname(String s) {this.pathname = s;}

    public String getPathname() {return this.pathname;}
}
