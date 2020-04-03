package view;

public interface Board {

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param playerId  integer representation of the player
   * @param numSpaces amount of spaces the player needs to move
   */
  public void movePlayer(int playerId, int numSpaces);

  /**
   * Control whether a property is displayed is active
   *
   * @param isActive the new status of the property
   * @param propertyId the property id
   */
  public void setPropertyActive(boolean isActive, int propertyId);

  /**
   * Control whether a property is displayed is mortgaged
   *
   * @param isMortgaged the new status of the property
   * @param propertyId the property id
   */
  public void setPropertyMortgaged(boolean isMortgaged, int propertyId);

}
