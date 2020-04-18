package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

public abstract class Event extends Tile {
    private String name;

    public void setName(String name) {this.name = name;}

    public String getName() {return this.name;}
}
