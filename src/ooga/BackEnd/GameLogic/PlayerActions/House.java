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

public class House {

    private final static List<String> option = List.of("OK");

    private String prompt = "";

    public House(Player currentPlayer, View view, Map<Integer, Player> map, boolean build) {
        if (currentPlayer.getProperties().size() < 1) {
            Decision d = new Decision("ERROR: You do not own any properties",option);
            view.makeUserDecision(d);
            return;
        }
        if (build) {
            buildHouseChecks(currentPlayer, view, build);
        }
        else {
            sellHouseChecks(currentPlayer, view, build);
        }
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(map);
    }

    private void buildHouseChecks(Player currentPlayer, View view, boolean build) {
        int check = 0;
        for (Property owned : currentPlayer.getProperties()) {
            if (currentPlayer.hasMonopoly(owned)) {
                check++;
            }
        }
        if (check < 1) {
            Decision d = new Decision("ERROR: You do not have a monopoly",option);
            view.makeUserDecision(d);
            return;
        }
        loopBuildSellHouse(currentPlayer, view, build);
    }

    private void sellHouseChecks(Player currentPlayer, View view, boolean build) {
        if (currentPlayer.getHouses() < 1) {
            Decision d = new Decision("ERROR: You do not have any houses to sell",option);
            view.makeUserDecision(d);
            return;
        }
        loopBuildSellHouse(currentPlayer, view, build);
    }

    private void loopBuildSellHouse(Player currentPlayer, View view, boolean build) {

        List<Property> options = filterHousingOptions(currentPlayer, build);
        MultiPropDecision d = new MultiPropDecision("Which property would you like to " + prompt + " a house on?",options);
        view.makeMultiDecision(d);

        Map<String, List<Property>> choicesMap = monopolyPropertiesMap(d.getChoice());

        Map<String, List<Property>> originalChoicesMap = monopolyPropertiesMap(d.getOptions());

        loop:
        for (Property owned : d.getChoice()) {
            for (String key : choicesMap.keySet()) {
                if (owned.getGroupNumber() == choicesMap.get(key).size()) {
                    if (build) {currentPlayer.buyHouse(1, (Street) owned);}
                    if (!build) {currentPlayer.sellHouse(1, (Street) owned);}
                    continue loop;
                }
                else if (owned.getGroupColor().equals(key)) {
                    for (Property w : originalChoicesMap.get(key)) {
                        if (w instanceof Street && owned instanceof Street && owned != w) {
                            int diff = ((Street) owned).getHouses() - ((Street) w).getHouses();
                            if ((diff > 0 && build) || (diff < 0 && !build)) {
                                Decision d1 = new Decision("ERROR: Houses must be distributed evenly",option);
                                view.makeUserDecision(d1);
                                break loop;
                            }
                        }
                    }
                    if (build) {currentPlayer.buyHouse(1, (Street) owned);}
                    if (!build) {currentPlayer.sellHouse(1, (Street) owned);}
                    continue loop;
                }
            }
        }
    }

    private List<Property> filterHousingOptions(Player currentPlayer, boolean build) {
        List<Property> temp = new ArrayList<>();
        for(Property q : currentPlayer.getProperties()) {
            if (q instanceof Street && !q.isMortgaged() && currentPlayer.hasMonopoly(q)) {
                if (((Street) q).getHouses() < 5 && build) {
                    temp.add((Street) q);
                    prompt = "buy";
                }
                if (((Street) q).getHouses() > 0 && !build) {
                    temp.add((Street) q);
                    prompt = "sell";
                }
            }
        }
        return temp;
    }

    private Map<String, List<Property>> monopolyPropertiesMap(List<Property> properties) {
        Map<String, List<Property>> temp = new HashMap<>();
        for (Property p : properties) {
            if (!temp.containsKey(p.getGroupColor())) {
                temp.put(p.getGroupColor(), new ArrayList<Property>());
            }
            temp.get(p.getGroupColor()).add(p);
        }
        return temp;
    }

}
