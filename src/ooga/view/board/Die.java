package ooga.view.board;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author jaidharosenblatt centred die that displays a given int roll. Depedent on images resources
 */
public class Die extends StackPane {

  private static final double SIZE = 60;
  private ImageView image;

  /**
   * Create a die of a given number
   * @param number number to display
   */
  public Die(int number) {
    image = new ImageView(getImage(number));
    image.setImage(getImage(number));
    image.setFitWidth(SIZE);
    image.setFitHeight(SIZE);
    getChildren().add(image);
  }

  private Image getImage(int number) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(number + ".png"));
  }


}
