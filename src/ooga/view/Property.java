package ooga.view;

public interface Property {

  /**
   * @return the category of this property
   */
  public String getCategory();

  /**
   * @return the cost of buying this property
   */
  public int getCost();

  /**
   * @return the rent this property charges
   */
  public int getRentPrice();

  /**
   * @return the cost of buying houses for this property
   */
  public int getHousePrice();

  /**
   * @return the path to the image to display for this property
   */
  public String getPathToImage();

  /**
   * @return the state (mortgaged or not)
   */
  public boolean isMortgaged();

  /**
   * Change the image for this property
   * @param pathToImage the path to the new image
   */
  public void changeImage(String pathToImage);
}
