package ooga.view.board;

import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.RailRoad;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Utility;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.util.PropertiesGetter;
import ooga.util.ViewException;

public class TileFactory {

  private double tileWidth;
  private double tileHeight;
  private double rowLength;

  protected TileFactory(double tileWidth, double tileHeight, double rowLength) {
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
    this.rowLength = rowLength;
  }

  protected TileView getPropertyFromTile(Tile tile, int index) {
    TileView t = getTileType(tile,index);
    t.setRotate(getRotation(index));
    return t;
  }

  private TileView getTileType(Tile t, int index) throws ViewException {
    String prefix = PropertiesGetter.getPromptFromKey("moneySign");
    if (t instanceof Street) {
      Property p = (Property) t;
      return p.convertView();
    } else if (t instanceof Utility) {
      return new UtilityTileView(((Utility) t).getTitle(), prefix + ((Utility) t).getCost(),
          ((Utility) t).getPathname(), tileWidth, tileHeight);
    } else if (t instanceof RailRoad) {
      return new UtilityTileView(((RailRoad) t).getTitle(), prefix + ((RailRoad) t).getCost(),
          ((RailRoad) t).getPathname(), tileWidth, tileHeight);
    } else if (t instanceof Event) {
      if (index % 10 == 0) {
        return new CornerTileView(((Event) t).getPathname(), tileWidth, tileHeight);
      }
      return new UtilityTileView(((Event) t).getBName(), "", ((Event) t).getPathname(), tileWidth,
          tileHeight);
    }
    throw new ViewException(PropertiesGetter.getErrorMessageFromKey("InvalidTile"));
  }

  private double getRotation(int index) {
    if (index <= rowLength) {
      return 0;
    }
    if (index <= rowLength * 2 - 1) {
      return 90;
    }
    if (index <= rowLength * 3) {
      return 180;
    } else {
      return 270;
    }
  }


}
