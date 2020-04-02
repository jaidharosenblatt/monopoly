package ooga;

public interface PlayerInfo {

  //List of Properties
  //Cash Balance
  //Position on Board
  //

  /**
   *
   * @return a List of properties that a player currently owns as well as any houses/hotels
   */
  public List<Property> getPropertiesUnmodifiable();

  /**
   *
   * @return amount of money the player has
   */
  public int getCashBalance();

  /**
   * @return Where on the board they are
   */
  public int getPositionOnBoard();
}
