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

public class Sell {

    public Sell(Player currentPlayer, View view, Map<Integer, Player> map) {
        if (currentPlayer.getHouses() < 1) {
            List<String> options = List.of("OK");
            Decision d = new Decision("ERROR: You do not have any houses to sell",options);
            view.makeUserDecision(d);
            return;
        }
        sellLoop(currentPlayer, view);
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(map);
    }

    private void sellLoop(Player p, View view) {
        List<Property> streets = new ArrayList<>();
        for(Property q : p.getProperties()) {
            if (q instanceof Street && !q.isMortgaged() && p.hasMonopoly(q)) {
                if (((Street) q).getHouses() > 0) {
                    streets.add((Street) q);
                }
            }
        }
        List<Property> options = streets;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to sell a house on?",options);
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
                    p.sellHouse(1, (Street) owned);
                    continue loop;
                }
                else if (owned.getGroupColor().equals(key)) {
                    for (Property w : originalMap.get(key)) {
                        if (w instanceof Street && owned instanceof Street && owned != w) {
                            int diff = ((Street) owned).getHouses() - ((Street) w).getHouses();
                            if (diff < 0) {
                                List<String> options1 = List.of("OK");
                                Decision d1 = new Decision("ERROR: Houses must be distributed evenly",options1);
                                view.makeUserDecision(d1);
                                break loop;
                            }
                        }
                    }
                    p.sellHouse(1, (Street) owned);
                    continue loop;
                }
            }
        }
    }
}
