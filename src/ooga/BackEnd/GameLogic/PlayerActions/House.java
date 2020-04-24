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

    public House(Player currentPlayer, View view, Map<Integer, Player> map, boolean build) {
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
        if (currentPlayer.getProperties().size() < 1) {
            Decision d = new Decision("ERROR: You do not own any properties",option);
            view.makeUserDecision(d);
            return;
        }
        int check = 0;
        for (Property owned : currentPlayer.getProperties()) {
            if (currentPlayer.hasMonopoly(owned)) {
                check++;
                if (owned instanceof Street) {
                    if (currentPlayer.getCashBalance() < ((Street) owned).getHouseCost()) {
                        Decision d = new Decision("ERROR: You do not have enough funds",option);
                        view.makeUserDecision(d);
                        return;
                    }
                }
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
        String prompt = "";
        List<Property> streets = new ArrayList<>();
        for(Property q : currentPlayer.getProperties()) {
            if (q instanceof Street && !q.isMortgaged() && currentPlayer.hasMonopoly(q)) {
                if (((Street) q).getHouses() < 5 && build) {
                    streets.add((Street) q);
                    prompt = "buy";
                }
                if (((Street) q).getHouses() > 0 && !build) {
                    streets.add((Street) q);
                    prompt = "sell";
                }
            }
        }
        List<Property> options = streets;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to " + prompt + " a house on?",options);
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
                    if (build) {currentPlayer.buyHouse(1, (Street) owned);}
                    if (!build) {currentPlayer.sellHouse(1, (Street) owned);}
                    continue loop;
                }
                else if (owned.getGroupColor().equals(key)) {
                    for (Property w : originalMap.get(key)) {
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

}
