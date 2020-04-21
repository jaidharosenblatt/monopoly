package ooga.api.objects;

public interface Board {

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param playerId  integer representation of the player
   * @param numSpaces amount of spaces the player needs to move
   */
  void movePlayer(int playerId, int numSpaces);

  /**
   * Set a space as "active" to change the display on board
   * @return the position of the space to set active
   */
  int setSpaceActive();

  /**
   * Set a space as "inactive" to change the display on board
   * @return the position of the space to set active
   */
  int setSpaceInactive();

}
