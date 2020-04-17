package ooga.view.board;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Die extends StackPane {

  private static final double SIZE = 40;
  private ImageView image;

  public Die(int number) {
    image = new ImageView(getImage(number));
    image.setImage(getImage(number));
    image.setFitWidth(SIZE);
    image.setFitHeight(SIZE);
    getChildren().add(image);
  }

  public void setRoll(int number) {
    image.setImage(getImage(number));
  }

  private Image getImage(int number) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(number + ".png"));
  }



}
