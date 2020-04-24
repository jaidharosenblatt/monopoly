package ooga.BackEnd.GameLogic;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameLogic.PlayerActions.House;
import ooga.BackEnd.GameLogic.PlayerActions.Mortgage;
import ooga.BackEnd.GameLogic.PlayerActions.Trade;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.util.PropertiesGetter;
import ooga.view.View;

import java.util.List;
import java.util.Map;

public class BankruptcyHandler {

    private static final List<String> option = List.of(PropertiesGetter.getPromptFromKey("Ok"));

    private View view;

    private List<Player> activePlayers;
    private Player currentPlayer;
    private Map<Integer, Player> playerTabMap;
    private boolean stopTurn;
    private int doubleTurns;

    /**
     * Handles the logic behind checking and dealing with a player that goes bankrupt
     *
     * @param view the visuals of the boardgame
     * @param activePlayers list of active players
     * @param currentPlayer current player of the turn
     * @param map key is the players' turn orders
     * @param doubleTurns int to determine if doubles were just rolled
     */

    public BankruptcyHandler(View view, List<Player> activePlayers, Player currentPlayer, Map<Integer, Player> playerTabMap, int doubleTurns) {
        this.view = view;
        this.activePlayers = activePlayers;
        this.currentPlayer = currentPlayer;
        this.playerTabMap = playerTabMap;
        this.doubleTurns = doubleTurns;
        stopTurn = false;
        isBankrupt();
    }

    private void isBankrupt() {
        if (currentPlayer.getCashBalance() < 0) {
            if (checkAssets()) {
                Decision d = new Decision(currentPlayer.getName() + " can afford to end bankruptcy without trading",option);
                view.makeUserDecision(d);
                stopTurn = true;
            }
            else {
                Decision d = new Decision(currentPlayer.getName() + " must trade to avoid bankruptcy",option);
                view.makeUserDecision(d);
                stopTurn = false;
                House s = new House(currentPlayer, view, playerTabMap, false);
                Mortgage m = new Mortgage(currentPlayer, view, playerTabMap, true);
                Trade t = new Trade(currentPlayer, view, activePlayers, playerTabMap);
            }
        }
        else {
            stopTurn = false;
        }
    }

    private boolean checkAssets() {
        int total = currentPlayer.getCashBalance();
        if (currentPlayer.getHouses() > 0) {
            for (Property s : currentPlayer.getProperties()) {
                if (s instanceof Street) {
                    if (((Street) s).getHouses() > 0) {
                        total += (((Street) s).getHouseCost() * ((Street) s).getHouses()) / 2;
                    }
                }
            }
        }
        for (Property q : currentPlayer.getProperties()) {
            if (!q.isMortgaged()) {
                total += (q.getCost() / 2);
            }
        }
        if (total >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if player's balance is below 0
     *
     * @return boolean of whether or not player's balance is below 0
     */

    public boolean doesPlayerLose() {
        //If player is negative after ending their turn, player loses
        if (currentPlayer.getCashBalance() < 0) {return true;}
        return false;
    }

    /**
     * Handles logic of player going bankrupt
     *
     */

    public void playerLoses() {
        Decision d = new Decision(currentPlayer.getName() + " went bankrupt!", option);
        view.makeUserDecision(d);

        //Make all of player's properties neutral again
        for (Property s : currentPlayer.getProperties()) {
            s.bankrupt();
            s.setOwner(null);
        }
        currentPlayer.setProperties(null);
        activePlayers.remove(currentPlayer);
        int remove = 0;
        for (int i : playerTabMap.keySet()) {
            if (playerTabMap.get(i) == currentPlayer) {
                remove = i;
            }
        }
        playerTabMap.remove(remove);
        doubleTurns = 0;

        //End the game if there's only one player left
        if (activePlayers.size() == 1) {
            Decision d1 = new Decision(activePlayers.get(0).getName() + " wins!", option);
            view.makeUserDecision(d1);
        }
    }

    /**
     * Checks if stopTurn is true or false
     *
     * @return boolean of whether or not to prevent turn from continuing
     */

    public boolean getStopTurn() {return stopTurn;}

    /**
     * Gets doubleTurns
     *
     * @return int
     */

    public int getDoubleTurns() {return doubleTurns;}

    /**
     * Gets activePlayers
     *
     * @return List of Players
     */

    public List<Player> getActivePlayers() {return activePlayers;}

    /**
     * Gets playerTabMap
     *
     * @return Map
     */

    public Map<Integer, Player> getPlayerTabMap() {return playerTabMap;}
}
