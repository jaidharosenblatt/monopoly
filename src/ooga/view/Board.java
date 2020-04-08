package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {

  private final static int NUMBER_OF_TILES = 40;
  private final static int ROW_LENGTH = 10;
  private List<Property> tiles = new ArrayList<>();

  public Board() {
    setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    createBoard();
    createGrid();
  }


  private void createGrid() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = tiles.get(i);
      if (i % ROW_LENGTH == 0) {
        continue;
      }
      if (i < ROW_LENGTH) {
        property.setRotate(180);
        add(property, i, 0);
      }
      if (i >= ROW_LENGTH && i < ROW_LENGTH * 2) {
        property.setRotate(270);
        add(property, ROW_LENGTH, i % ROW_LENGTH);
      }
      if (i >= ROW_LENGTH * 2 && i < ROW_LENGTH * 3) {
        int backwardsIndex = ROW_LENGTH * 3 - i % ROW_LENGTH;
        property = tiles.get(backwardsIndex);
        add(property, i - 2 * ROW_LENGTH, ROW_LENGTH);
      }
      if (i >= ROW_LENGTH * 3) {
        int backwardsIndex = ROW_LENGTH * 4 - i % ROW_LENGTH;
        property = tiles.get(backwardsIndex);
        property.setRotate(90);
        add(property, 0, i % ROW_LENGTH);
      }
    }
    add(new Property(Color.GREEN, "go", 0),0,0);
    add(new Property(Color.GREEN, "go", 0),10,0);
    add(new Property(Color.GREEN, "go", 0),10,10);
    add(new Property(Color.GREEN, "go", 0),0,10);




  }


  private void createBoard() {
    for (int i = 0; i < NUMBER_OF_TILES; i++) {
      Property property = new Property(Color.BLACK, "hi", i);
      tiles.add(property);
    }
  }

}
