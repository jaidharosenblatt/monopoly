package ooga.api.view;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.List;

public interface MultiDecision {


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
    List<Property> getOptions();

    /**
     * Set the choice that the player picked in the gui
     *
     */
    void setChoice(Property p);

    Property getChoice();
}
