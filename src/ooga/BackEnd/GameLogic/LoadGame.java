package ooga.BackEnd.GameLogic;

import java.util.*;

import javafx.stage.Stage;
import ooga.BackEnd.GameLogic.PlayerActions.*;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.CardTile;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

import ooga.api.objects.PlayerInfo;
import ooga.view.View;

public class LoadGame {

    private static final int JAIL_INDEX = 10;

    private View view;

    private List<PlayerInfo> playerInfoList;
    private List<Player> activePlayers;
    private Player currentPlayer;
    private Map<Integer, Player> playerTabMap;

    private List<Tile> allTiles;
    private List<Property> properties;
    private List<Event> eventTiles;

    private int currentIndex;
    private int doubleTurns;

    public LoadGame(String game_pathname, Map<String, String> playerInfo, Stage stage) throws FileNotFoundException, XMLStreamException {

        XMLParser parse = new XMLParser(game_pathname);
        this.properties = (ArrayList<Property>) parse.properties.clone();
        this.eventTiles = (ArrayList<Event>) parse.eventTiles.clone();
        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();
        this.doubleTurns = 0;

        CreatePlayers cp = new CreatePlayers(playerInfo, allTiles);
        activePlayers = cp.getActivePlayers();
        playerTabMap = cp.getPlayerTabMap();
        currentPlayer = activePlayers.get(0);
        currentIndex = 0;

        playerInfoList = new ArrayList<>();
        playerInfoList.addAll(activePlayers);

        view = new View(stage, this, playerInfoList, allTiles);
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(playerTabMap);

        for (Tile t : allTiles) {
            t.setView(view);
        }
        for (Player p : activePlayers) {
            p.setView(view);
        }
    }

    public void takeTurn(){
        BankruptcyHandler bank = new BankruptcyHandler(view, activePlayers, currentPlayer, playerTabMap, doubleTurns);

        //Only if player can avoid bankruptcy without trading with other players
        if (bank.getStopTurn()) {
            return;
        }
        else {

            if (bank.doesPlayerLose()) {
                bank.playerLoses();
                activePlayers = bank.getActivePlayers(); doubleTurns = bank.getDoubleTurns(); playerTabMap = bank.getPlayerTabMap();
            }

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
            view.refreshPlayers(playerTabMap);
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
            if (e instanceof CardTile) {
                ((CardTile) e).playerList(this.activePlayers);
                ((CardTile) e).updateProps(this.properties);
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
        int new_tile = p.getPositionOnBoard() + p.dice1 + p.dice2;
        if (new_tile > 39) {
            new_tile -= 40;
            p.receive(200);
        }
        p.moveTo(new_tile);
    }

    private void doubles() {
        doubleTurns++;
    }

    public void build() {House b = new House(currentPlayer, view, playerTabMap, true);}

    public void sell() {House s = new House(currentPlayer, view, playerTabMap, false);}

    public void mortgage() {Mortgage m = new Mortgage(currentPlayer, view, playerTabMap);}

    public void unmortgage() {Unmortgage u = new Unmortgage(currentPlayer, view, playerTabMap);}

    public void trade() {Trade t = new Trade(currentPlayer, view, activePlayers, playerTabMap);}

}
