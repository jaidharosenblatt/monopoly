package ooga.BackEnd.GameLogic.Decisions;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.ArrayList;
import java.util.List;

public class MultiPropDecision implements ooga.api.objects.MultiPropDecision {

    private String prompt;
    private List<Property> options;
    private ArrayList<Property> choices;

    /**
     * Holds the list of options available to the player and answer once selected
     *
     * @param prompt a string of the message displayed to the player
     * @param options a list of Properties containing the possible choices
     */

    public MultiPropDecision(String prompt, List<Property> options) {
        this.prompt = prompt;
        this.options = options;
        this.choices = new ArrayList<>();
    }

    /**
     * Display a prompt for this decision
     *
     * @return the prompt to display to the player
     */

    @Override
    public String getPrompt() {return prompt;}

    /**
     * Display the choices available to the player
     *
     * @return a list of possible choices as Properties
     */

    @Override
    public List<Property> getOptions() {return options;}

    /**
     * Set the choice that the player picked in the gui
     *
     */

    @Override
    public void setChoice(Property p) {this.choices.add(p);}

    /**
     * Remove the choice that the player unselected in the gui
     *
     */

    @Override
    public void remChoice(Property p) {this.choices.remove(p);}

    /**
     * Get the choice(s) that the player picked in the gui
     *
     * @return a list of selected properties
     */

    @Override
    public List<Property> getChoice() {return this.choices;}
}
