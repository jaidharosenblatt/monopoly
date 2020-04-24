package ooga.BackEnd.GameLogic.Decisions;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.ArrayList;
import java.util.List;

public class MultiPlayerDecision implements ooga.api.objects.MultiPlayerDecision {

    private String prompt;
    private List<Player> options;
    private ArrayList<Player> choices;

    /**
     * Holds the list of options available to the player and answer once selected
     *
     * @param prompt a string of the message displayed to the player
     * @param options a list of Players containing the possible choices
     */

    public MultiPlayerDecision(String prompt, List<Player> options) {
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
     * @return a list of possible choices as Players
     */

    @Override
    public List<Player> getOptions() {return options;}

    /**
     * Set the choice that the player picked in the gui
     *
     */

    @Override
    public void setChoice(Player p) {this.choices.add(p);}

    /**
     * Remove the choice that the player unselected in the gui
     *
     */

    @Override
    public void remChoice(Player p) {this.choices.remove(p);}

    /**
     * Get the choice(s) that the player picked in the gui
     *
     * @return a list of selected players
     */

    @Override
    public List<Player> getChoice() {return this.choices;}
}
