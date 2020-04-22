package ooga.BackEnd.GameLogic.PlayerActions;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameLogic.Decisions.MultiPropDecision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.view.View;

import java.util.ArrayList;
import java.util.List;

public class Unmortgage {
    public Unmortgage(Player currentPlayer, View view) {
        if (currentPlayer.getProperties().size() < 1) {
            List<String> option = List.of("OK");
            Decision d = new Decision("ERROR: You do not have an properties",option);
            view.makeUserDecision(d);
            return;
        }
        ArrayList<Property> filter = new ArrayList<>();
        for (Property p: currentPlayer.getProperties()) {
            if (p.isMortgaged()) {
                filter.add(p);
            }
        }

        List<Property> options = filter;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to unmortgage?",options);
        view.makeMultiDecision(d);

        for (Property p : d.getChoice()) {
            if (p.isMortgaged()) {
                p.liftMortgage();
            }
        }
    }
}
