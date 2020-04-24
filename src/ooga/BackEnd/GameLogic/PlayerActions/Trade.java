package ooga.BackEnd.GameLogic.PlayerActions;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameLogic.Decisions.MultiPlayerDecision;
import ooga.BackEnd.GameLogic.Decisions.MultiPropDecision;
import ooga.BackEnd.GameLogic.Decisions.StringDecision;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.util.PropertiesGetter;
import ooga.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trade {

    /**
     * Handles the logic behind a player trading items with another player.
     * Updates player, property, and view classes based on GUI decisions.
     *
     * @param currentPlayer current player of the turn
     * @param view the visuals of the boardgame
     * @param activePlayers a list of all players in the game
     * @param map key is the players' turn orders
     */

    public Trade(Player currentPlayer, View view, List<Player> activePlayers, Map<Integer, Player> map) {
        ArrayList<Player> temp = new ArrayList<>();
        for (Player a : activePlayers) {
            if (currentPlayer != a) {
                temp.add(a);
            }
        }
        List<Player> options = temp;
        MultiPlayerDecision decisionPlayer = new MultiPlayerDecision("Which player would you like to trade with?", options);
        view.makeMultiPlayerDecision(decisionPlayer);

        if (decisionPlayer.getChoice().size() == 0) {
            return;
        }

        List<Property> options1 = decisionPlayer.getChoice().get(0).getProperties();
        MultiPropDecision decisionPropWant = new MultiPropDecision("Which property do you want from " + decisionPlayer.getChoice().get(0).getName() + "?",options1);
        view.makeMultiDecision(decisionPropWant);

        StringDecision decisionCashWant = new StringDecision("How much money do you want from " + decisionPlayer.getChoice().get(0).getName() + "?");
        view.makeStringDecision(decisionCashWant);

        decisionCashWant = new StringDecision("How much money do you want from " + decisionPlayer.getChoice().get(0).getName() + "?");
        view.makeStringDecision(decisionCashWant);

        List<Property> options2 = currentPlayer.getProperties();
        MultiPropDecision decisionPropGive = new MultiPropDecision("Which property will you offer to " + decisionPlayer.getChoice().get(0).getName() + "?",options2);
        view.makeMultiDecision(decisionPropGive);

        StringDecision decisionCashGive = new StringDecision("How much money will you offer to " + decisionPlayer.getChoice().get(0).getName() + "?");
        view.makeStringDecision(decisionCashGive);

        decisionCashGive = new StringDecision("How much money will you offer to " + decisionPlayer.getChoice().get(0).getName() + "?");
        view.makeStringDecision(decisionCashGive);

        int cashGive = 0;
        if (isNumeric(decisionCashGive.getChoice())) {
            cashGive = Integer.parseInt(decisionCashGive.getChoice());
        }
        int cashWant = 0;
        if (isNumeric(decisionCashWant.getChoice())) {
            cashWant = Integer.parseInt(decisionCashWant.getChoice());
        }

        List<String> option = List.of(PropertiesGetter.getPromptFromKey("Yes"), PropertiesGetter.getPromptFromKey("No"));
        Decision d = new Decision("Does " + decisionPlayer.getChoice().get(0).getName() + " accept the offer?",option);
        view.makeUserDecision(d);

        if (d.getChoice().equals(PropertiesGetter.getPromptFromKey("Yes"))) {
            currentPlayer.trade(cashGive, decisionPropGive.getChoice(),
                    decisionPlayer.getChoice().get(0), cashWant, decisionPropWant.getChoice());
        }
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(map);
    }

    private boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
}
