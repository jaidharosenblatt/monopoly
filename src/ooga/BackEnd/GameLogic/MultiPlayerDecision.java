package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.ArrayList;
import java.util.List;

public class MultiPlayerDecision implements ooga.api.objects.MultiPlayerDecision {

    private String prompt;
    private List<Player> options;
    private ArrayList<Player> choices;

    public MultiPlayerDecision(String prompt, List<Player> options) {
        this.prompt = prompt;
        this.options = options;
        this.choices = new ArrayList<>();
    }

    @Override
    public String getPrompt() {return prompt;}

    @Override
    public List<Player> getOptions() {return options;}

    @Override
    public void setChoice(Player p) {this.choices.add(p);}

    @Override
    public void remChoice(Player p) {this.choices.remove(p);}

    @Override
    public ArrayList<Player> getChoice() {return this.choices;}
}
