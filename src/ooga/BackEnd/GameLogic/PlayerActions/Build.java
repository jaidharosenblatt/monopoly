package ooga.BackEnd.GameLogic.PlayerActions;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameLogic.Decisions.MultiPropDecision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Build {

    public Build(Player currentPlayer, View view) {
        if (currentPlayer.getProperties().size() < 1) {
            List<String> options = List.of("OK");
            Decision d = new Decision("ERROR: You do not own any properties",options);
            view.makeUserDecision(d);
            return;
        }
        int check = 0;
        for (Property owned : currentPlayer.getProperties()) {
            if (currentPlayer.hasMonopoly(owned)) {
                check++;
                if (owned instanceof Street) {
                    if (currentPlayer.getBalance() < ((Street) owned).getHouseCost()) {
                        List<String> options = List.of("OK");
                        Decision d = new Decision("ERROR: You do not have enough funds",options);
                        view.makeUserDecision(d);
                        return;
                    }
                }
            }
        }
        if (check < 1) {
            List<String> options = List.of("OK");
            Decision d = new Decision("ERROR: You do not have a monopoly",options);
            view.makeUserDecision(d);
            return;
        }
        buildLoop(currentPlayer, view);
    }

    private void buildLoop(Player p, View view) {

        List<Property> streets = new ArrayList<>();
        for(Property q : p.getProperties()) {
            if (q instanceof Street && !q.isMortgaged() && p.hasMonopoly(q)) {
                if (((Street) q).getHouses() < 5) {
                    streets.add((Street) q);
                }
            }
        }
        List<Property> options = streets;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to buy a house on?",options);
        view.makeMultiDecision(d);

        Map<String, ArrayList<Property>> choicemap = new HashMap<>();
        for (Property r : d.getChoice()) {
            if (!choicemap.containsKey(r.getGroupColor())) {
                choicemap.put(r.getGroupColor(), new ArrayList<Property>());
            }
            choicemap.get(r.getGroupColor()).add(r);
        }

        Map<String, ArrayList<Property>> originalMap = new HashMap<>();
        for (Property opt : d.getOptions()) {
            if (!originalMap.containsKey(opt.getGroupColor())) {
                originalMap.put(opt.getGroupColor(), new ArrayList<Property>());
            }
            originalMap.get(opt.getGroupColor()).add(opt);
        }

        loop:
        for (Property owned : d.getChoice()) {
            for (String key : choicemap.keySet()) {
                if (owned.getGroupNumber() == choicemap.get(key).size()) {
                    p.buyHouse(1, (Street) owned);
                    continue loop;
                }
                else if (owned.getGroupColor().equals(key)) {
                    for (Property w : originalMap.get(key)) {
                        if (w instanceof Street && owned instanceof Street && owned != w) {
                            int diff = ((Street) owned).getHouses() - ((Street) w).getHouses();
                            if (diff > 0) {
                                List<String> options1 = List.of("OK");
                                Decision d1 = new Decision("ERROR: Houses must be distributed evenly",options1);
                                view.makeUserDecision(d1);
                                break loop;
                            }
                        }
                    }
                    p.buyHouse(1, (Street) owned);
                    continue loop;
                }
            }
        }
    }

}
