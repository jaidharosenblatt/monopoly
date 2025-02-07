package view;

public interface Property {

  /**
   * @return the category of this property
   */
  String getCategory();

  /**
   * @return the cost of buying this property
   */
  int getCost();

  /**
   * @return the rent this property charges
   */
  int getRentPrice();

  /**
   * @return the cost of buying houses for this property
   */
  int getHousePrice();

  /**
   * @return the path to the image to display for this property
   */
  String getPathToImage();

  /**
   * @return the state (mortgaged or not)
   */
  boolean isMortgaged();

  /**
   * Change the image for this property
   * @param pathToImage the path to the new image
   */
  void changeImage(String pathToImage);
}
