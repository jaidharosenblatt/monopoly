package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.ArrayList;
import java.util.List;

public class MultiDecision implements ooga.api.view.MultiDecision {

    private String prompt;
    private List<Property> options;
    private ArrayList<Property> choices;

    public MultiDecision(String prompt, List<Property> options) {
        this.prompt = prompt;
        this.options = options;
        this.choices = new ArrayList<>();
    }

    @Override
    public String getPrompt() {return prompt;}

    @Override
    public List<Property> getOptions() {return options;}

    @Override
    public void setChoice(Property p) {this.choices.add(p);}

    @Override
    public void remChoice(Property p) {this.choices.remove(p);}

    @Override
    public ArrayList<Property> getChoice() {return this.choices;}
}
