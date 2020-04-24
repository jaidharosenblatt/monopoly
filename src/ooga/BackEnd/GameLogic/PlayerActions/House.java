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

        //The maps contain the color and a list of all matching properties from that color set
        Map<String, List<Property>> originalChoicesMap = monopolyPropertiesMap(d.getOptions());
        Map<String, List<Property>> selectedChoicesMap = monopolyPropertiesMap(d.getChoice());
        
        loop:
        for (Property chosen : d.getChoice()) {
            //Checks to see if user selected entire monopoly for that property color
            //No need to check for house evenness
            for (String key : selectedChoicesMap.keySet()) {
                if (chosen.getGroupNumber() == selectedChoicesMap.get(key).size() &&
                    chosen.getGroupColor().equals(selectedChoicesMap.get(key))) {
                    if (build) {currentPlayer.buyHouse(1, (Street) chosen);}
                    if (!build) {currentPlayer.sellHouse(1, (Street) chosen);}
                    continue loop;
                }
            }
            //Checks to see if property has one more or one less than the other properties in that color set
            for (String key : originalChoicesMap.keySet()) {
                for (Property original : originalChoicesMap.get(key)) {
                    int diff = ((Street) chosen).getHouses() - ((Street) original).getHouses();
                    if ((diff > 0 && build) || (diff < 0 && !build)) {
                        Decision d1 = new Decision("ERROR: Houses must be distributed evenly",option);
                        view.makeUserDecision(d1);
                        break loop;
                    }
                }
            }
            //If property reaches this point, it is a singular property without distribution issues
            if (build) {currentPlayer.buyHouse(1, (Street) chosen);}
            if (!build) {currentPlayer.sellHouse(1, (Street) chosen);}
            continue loop;
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
