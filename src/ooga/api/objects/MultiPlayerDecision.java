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
     * @return a list of possible choices as Properties
     */
    List<Player> getOptions();

    /**
     * Set the choice that the player picked in the gui
     *
     */
    void setChoice(Player p);

    void remChoice(Player p);

    List<Player> getChoice();
}
