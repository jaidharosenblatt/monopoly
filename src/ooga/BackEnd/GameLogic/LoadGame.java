package ooga.BackEnd.GameLogic;

import java.awt.*;
import java.util.*;

import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameLogic.Decisions.MultiPlayerDecision;
import ooga.BackEnd.GameLogic.Decisions.MultiPropDecision;
import ooga.BackEnd.GameLogic.Decisions.StringDecision;
import ooga.BackEnd.GameLogic.PlayerActions.*;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.cardTile;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.RailRoad;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Utility;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

import ooga.api.objects.PlayerInfo;
import ooga.view.View;

public class LoadGame {

    private static final int JAIL_INDEX = 10;

    private ArrayList<Property> properties;
    private ArrayList<Street> streets;
    private ArrayList<RailRoad> railroads;
    private ArrayList<Utility> utilities;
    private ArrayList<Event> eventTiles;
    private ArrayList<Tile> allTiles;
    private ArrayList<Player> activePlayers;
    private ArrayList<PlayerInfo> playerInfoList;
    private View view;
    private Player currentPlayer;
    private int currentIndex;
    private int doubleTurns;
    private Map<Integer, Player> map;
    private boolean stopTurn;

    public LoadGame(String game_pathname, int player_number, Stage stage) throws FileNotFoundException, XMLStreamException {

        XMLParser parse = new XMLParser(game_pathname);
        this.stopTurn = false;
        this.properties = (ArrayList<Property>) parse.properties.clone();
        this.eventTiles = (ArrayList<Event>) parse.eventTiles.clone();
        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();
        this.doubleTurns = 0;

        createPlayers(player_number);
        currentPlayer = activePlayers.get(0);
        currentIndex = 0;

        ///////////////////////////////////////////////////////////////////////////////////
        //TESTING PURPOSES ONLY: last player in turn gets all available properties
//        ArrayList<Property> test = new ArrayList<>();
//        for (Tile t : allTiles) {
//            if (t.getBoardIndex() < 40 && t instanceof Property) {
//                ((Property) t).setOwner(currentPlayer);
//                test.add((Property) t);
//            }
//        }
//        currentPlayer.setProperties(test);
        //DELETE AFTER FINISHING TESTING
        ///////////////////////////////////////////////////////////////////////////////////

        playerInfoList = new ArrayList<>();
        playerInfoList.addAll(activePlayers);

        view = new View(stage, this, playerInfoList, allTiles);
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(map);

        for (Tile t : allTiles) {
            t.setView(view);
        }
        for (Player p : activePlayers) {
            p.setView(view);
        }
    }

    private void createPlayers(int player_number) {
        this.activePlayers = new ArrayList<>();
        this.map = new HashMap<>();
        List<Color> colors = List.of(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        Player[] temp = new Player[player_number];
        for (int i = 0; i < player_number; i++) {
            //----------------------------------------------
            //INPUT FRONT-END TEXT-FIELD USER-INTERFACE HERE
            //----------------------------------------------
            temp[i] = new Player("Player " + (i + 1), this.allTiles);
            temp[i].setColor(colors.get(i));
        }
        temp = rollForOrder(temp);
        for (Player p : temp) {
            this.activePlayers.add(p);
            this.map.put(activePlayers.indexOf(p), p);
        }
    }

    private Player[] rollForOrder(Player[] list) {
        Player[] order = new Player[list.length];
        int counter = 0;
        ArrayList<Integer> chosen = new ArrayList<>();
        while (chosen.size() < list.length) {
            int probality = (int) (Math.random() * list.length);
            if (!chosen.contains(probality)) {
                chosen.add(probality);
                order[counter] = list[probality];
                counter++;
            }
        }
        return order;
    }

    public void takeTurn(){
        isBankrupt();
        //Only if player can avoid bankruptcy without trading with other players
        if (stopTurn) {
            return;
        }
        else {

            doesPlayerLose();

            //Prevents player that rolled doubles from leaving jail
            if (currentPlayer.isJailed()) {doubleTurns = 0;}

            //if doubles were rolled, stay on the current player
            if (doubleTurns == 0) {nextPlayer();}

            view.setCurrentPlayer(currentPlayer);
            updateCardTiles();

            //Player either moves normally or completes jail action
            if (currentPlayer.isJailed()) {
                allTiles.get(JAIL_INDEX).onTile(currentPlayer);
                allTiles.get(JAIL_INDEX).action();
            }
            else {rollDiceAndMove(currentPlayer);}

            //Update player tab
            view.refreshPlayers(map);
        }
    }

    private void nextPlayer(){
        if (currentIndex + 1 >= activePlayers.size()) {
            currentPlayer = activePlayers.get(0);
            currentIndex = 0;
        }
        else {
            currentPlayer = activePlayers.get(currentIndex + 1);
            currentIndex++;
        }
    }

    private void updateCardTiles() {
        for (Event e : this.eventTiles) {
            if (e instanceof cardTile) {
                ((cardTile) e).playerList(this.activePlayers);
                ((cardTile) e).updateProps(this.properties);
            }
        }
    }

    private void rollDiceAndMove(Player p) {
        p.rollDice();
        if (p.dice1 == p.dice2) {doubles();}
        if (doubleTurns == 3) {
            p.setJailed();
            return;
        }
        if (doubleTurns > 0 && p.dice1 != p.dice2) {doubleTurns = 0;}
        int new_tile = p.getTile() + p.dice1 + p.dice2;
        if (new_tile > 39) {
            new_tile -= 40;
            p.receive(200);
        }
        p.moveTo(new_tile);
    }

    private void doubles() {
        doubleTurns++;
    }

    public void build() {Build b = new Build(currentPlayer, view, map);}

    public void sell() {Sell s = new Sell(currentPlayer, view, map);}

    public void mortgage() {Mortgage m = new Mortgage(currentPlayer, view, map);}

    public void unmortgage() {Unmortgage u = new Unmortgage(currentPlayer, view, map);}

    public void trade() {Trade t = new Trade(currentPlayer, view, activePlayers, map);}

    private void isBankrupt() {
        if (currentPlayer.getBalance() < 0) {
            if (checkAssets()) {
                List<String> options = List.of("OK");
                Decision d = new Decision(currentPlayer.getName() + " can afford to end bankruptcy without trading",options);
                view.makeUserDecision(d);
                stopTurn = true;
            }
            else {
                List<String> options = List.of("OK");
                Decision d = new Decision(currentPlayer.getName() + " must trade to avoid bankruptcy",options);
                view.makeUserDecision(d);
                stopTurn = false;
                sell();
                mortgage();
                trade();
            }
        }
        else {
            stopTurn = false;
        }
    }

    private boolean checkAssets() {
        int total = currentPlayer.getBalance();
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

    private void doesPlayerLose() {
        //If player is negative after ending their turn, player loses
        if (currentPlayer.getBalance() < 0) {

            List<String> options = List.of("OK");
            Decision d = new Decision(currentPlayer.getName() + " went bankrupt!", options);
            view.makeUserDecision(d);

            //Make all of player's properties neutral again
            for (Property s : currentPlayer.getProperties()) {
                s.bankrupt();
                s.setOwner(null);
            }
            currentPlayer.setProperties(null);
            activePlayers.remove(currentPlayer);
            int remove = 0;
            for (int i : map.keySet()) {
                if (map.get(i) == currentPlayer) {
                    remove = i;
                }
            }
            map.remove(remove);
            doubleTurns = 0;

            //End the game if there's only one player left
            if (activePlayers.size() == 1) {
                List<String> options2 = List.of("OK");
                Decision d1 = new Decision(activePlayers.get(0).getName() + " wins!", options2);
                view.makeUserDecision(d1);
            }
        }
    }

}
