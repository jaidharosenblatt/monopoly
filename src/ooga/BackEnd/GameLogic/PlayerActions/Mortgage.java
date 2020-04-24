package ooga.BackEnd.GameLogic.PlayerActions;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameLogic.Decisions.MultiPropDecision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mortgage {

    private final static List<String> option = List.of("OK");

    public Mortgage(Player currentPlayer, View view, Map<Integer, Player> map, boolean mortgage) {
        String prompt = "";
        if (currentPlayer.getProperties().size() < 1) {
            Decision d = new Decision("ERROR: You do not have any properties",option);
            view.makeUserDecision(d);
            return;
        }
        ArrayList<Property> filter = new ArrayList<>();
        for (Property p: currentPlayer.getProperties()) {
            if (!p.isMortgaged() && mortgage) {
                prompt = "mortgage";
                if (p instanceof Street) {
                    if (((Street) p).getHouses() > 0) {
                        continue;
                    }
                }
                filter.add(p);
            }
            if (p.isMortgaged() && !mortgage) {
                filter.add(p);
            }
        }

        List<Property> options = filter;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to " + prompt + "?",options);
        view.makeMultiDecision(d);

        for (Property p : d.getChoice()) {
            if (!(p.isMortgaged()) && mortgage) {
                p.setMortgaged();
            }
            if (p.isMortgaged() && !mortgage) {
                p.liftMortgage();
            }

        }
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(map);
    }
}
