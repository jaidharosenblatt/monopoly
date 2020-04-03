package view;

public interface Board {

  /**
   * Use this method to animate the movement of a players token to a new board space
   *
   * @param playerId  integer representation of the player
   * @param numSpaces amount of spaces the player needs to move
   */
  void movePlayer(int playerId, int numSpaces);

  /**
   * @return the status of a space
   */
  int getSpaceActive();

}
