package ooga.api.objects;

import ooga.BackEnd.GameObjects.Player;

import java.util.List;

public interface MultiPlayerDecision {


    /**
     * Display a prompt for this decision
     *
     * @return the prompt to display to the player
     */
    String getPrompt();

    /**
     * Display the choices available to the player
     *
     * @return a list of possible choices as Players
     */
    List<Player> getOptions();

    /**
     * Set the choice that the player picked in the gui
     *
     */
    void setChoice(Player p);

    /**
     * Remove the choice that the player unselected in the gui
     *
     */

    void remChoice(Player p);

    /**
     * Get the choice(s) that the player picked in the gui
     *
     * @return a list of selected players
     */

    List<Player> getChoice();
}
