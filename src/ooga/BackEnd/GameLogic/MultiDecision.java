package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.List;

public class MultiDecision implements ooga.api.view.MultiDecision {

    private String prompt;
    private List<Property> options;
    private Property choice;

    public MultiDecision(String prompt, List<Property> options) {
        this.prompt = prompt;
        this.options = options;
    }

    @Override
    public String getPrompt() {return prompt;}

    @Override
    public List<Property> getOptions() {return options;}

    @Override
    public void setChoice(Property p) {this.choice = p;}

    @Override
    public Property getChoice() {return this.choice;}
}
