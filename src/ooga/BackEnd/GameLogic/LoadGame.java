package ooga.BackEnd.GameLogic;

import java.awt.*;
import java.util.*;

import javafx.stage.Stage;
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
    private Iterator<Player> itr;
    private View view;
    private Player currentPlayer;
    private int doubleTurns;
    private Map<Integer, Player> map;

    public LoadGame(String game_pathname, int player_number, Stage stage) throws FileNotFoundException, XMLStreamException {

        XMLParser parse = new XMLParser(game_pathname);

        this.properties = (ArrayList<Property>) parse.properties.clone();
        this.eventTiles = (ArrayList<Event>) parse.eventTiles.clone();
        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();
        this.doubleTurns = 0;

        createPlayers(player_number);
        currentPlayer = activePlayers.get(0);

        ///////////////////////////////////////////////////////////////////////////////////
        //TESTING PURPOSES ONLY: last player in turn gets the first 12 available properties
        ArrayList<Property> test = new ArrayList<>();
        for (Tile t : allTiles) {
            if (t.getBoardIndex() < 12 && t instanceof Property) {
                ((Property) t).setOwner(currentPlayer);
                test.add((Property) t);
            }
        }
        currentPlayer.setProperties(test);
        //DELETE AFTER FINISHING TESTING
        ///////////////////////////////////////////////////////////////////////////////////

         playerInfoList = new ArrayList<>();
        playerInfoList.addAll(activePlayers);

        view = new View(stage, this, playerInfoList, allTiles);

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
            this.map.put(i, temp[i]);
        }
        temp = rollForOrder(temp);
        for (Player p : temp) {
            this.activePlayers.add(p);
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
        if (doubleTurns == 0) {
            nextPlayer();
        }
        displayAssets(currentPlayer);
        view.setCurrentPlayer(currentPlayer);
        view.refreshPlayers(map);
        updateCardTiles();
        if (currentPlayer.isJailed()) {
            allTiles.get(JAIL_INDEX).action();
        }
        else {
            rollDiceAndMove(currentPlayer);
        }
    }

    private void nextPlayer(){
        int i = activePlayers.indexOf(currentPlayer);
        if (i+1 >= activePlayers.size()){
            currentPlayer = activePlayers.get(0);
        }
        else {
            currentPlayer = activePlayers.get(i+1);
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
        if (doubleTurns == 3) {p.setJailed();}
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

    public void build() {
        if (currentPlayer.getProperties().size() < 1) {
            List<String> options = List.of("OK");
            Decision d = new Decision("ERROR: You do not own any properties",options);
            view.makeUserDecision(d);
            return;
        }
        int check = 0;
        for (Property owned : currentPlayer.getProperties()) {
            if (currentPlayer.hasMonopoly(owned)) {
                check++;
                if (owned instanceof Street) {
                    if (currentPlayer.getBalance() < ((Street) owned).getHouseCost()) {
                        List<String> options = List.of("OK");
                        Decision d = new Decision("ERROR: You do not have enough funds",options);
                        view.makeUserDecision(d);
                        return;
                    }
                }
            }
        }
        if (check < 1) {
            List<String> options = List.of("OK");
            Decision d = new Decision("ERROR: You do not have a monopoly",options);
            view.makeUserDecision(d);
            return;
        }
        buildLoop(currentPlayer);
    }

    private void buildLoop(Player p) {

        List<Property> streets = new ArrayList<>();
        for(Property q : p.getProperties()) {
            if (q instanceof Street && !q.isMortgaged() && p.hasMonopoly(q)) {
                if (((Street) q).getHouses() < 5) {
                    streets.add((Street) q);
                }
            }
        }
        List<Property> options = streets;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to buy a house on?",options);
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
                    p.buyHouse(1, (Street) owned);
                    continue loop;
                }
                else if (owned.getGroupColor().equals(key)) {
                    for (Property w : originalMap.get(key)) {
                        if (w instanceof Street && owned instanceof Street && owned != w) {
                            int diff = ((Street) owned).getHouses() - ((Street) w).getHouses();
                            if (diff > 0) {
                                List<String> options1 = List.of("OK");
                                Decision d1 = new Decision("ERROR: Houses must be distributed evenly",options1);
                                view.makeUserDecision(d1);
                                break loop;
                            }
                        }
                    }
                    p.buyHouse(1, (Street) owned);
                    continue loop;
                }
            }
        }
    }

    public void sell() {
        if (currentPlayer.getHouses() < 1) {
            List<String> options = List.of("OK");
            Decision d = new Decision("ERROR: You do not have any houses to sell",options);
            view.makeUserDecision(d);
            return;
        }
        sellLoop(currentPlayer);
    }

    private void sellLoop(Player p) {
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

    public void mortgage() {
        if (currentPlayer.getProperties().size() < 1) {
            List<String> option = List.of("OK");
            Decision d = new Decision("ERROR: You do not have an properties",option);
            view.makeUserDecision(d);
            return;
        }
        ArrayList<Property> filter = new ArrayList<>();
        for (Property p: currentPlayer.getProperties()) {
            if (!p.isMortgaged()) {
                filter.add(p);
            }
        }

        List<Property> options = filter;
        MultiPropDecision d = new MultiPropDecision("Which property would you like to mortgage?",options);
        view.makeMultiDecision(d);

        for (Property p : d.getChoice()) {
            if (!(p.isMortgaged())) {
                p.setMortgaged();
            }
        }
    }

    public void unmortgage() {
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

    public void trade() {
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

        List<String> option = List.of("Yes", "No");
        Decision d = new Decision("Does " + decisionPlayer.getChoice().get(0).getName() + " accept the offer?",option);
        view.makeUserDecision(d);

        if (d.getChoice().equals("Yes")) {
            currentPlayer.trade(cashGive, decisionPropGive.getChoice(),
                    decisionPlayer.getChoice().get(0), cashWant, decisionPropWant.getChoice());
        }
    }

//      updateCardTiles();


//      int game = 0;
//        String input = "";
//        while(game != 1) {
//            this.itr = this.activePlayers.iterator();
//            while (itr.hasNext()) {
//                Player p = itr.next();
//                view.setCurrentPlayer(p);
//                if (activePlayers.size() == 1) {
//                    System.out.println(activePlayers.get(0).getName() + " wins!");
//                    itr.remove();
//                    game = 1;
//                    break;
//                }
//                if (p.isJailed()) {
//                    p.moveTo(JAIL_INDEX);
//                    if (p.isJailed()) {
//                        System.out.println("You remain in jail");
//                        promptPlayer(p);
//                        continue;
//                    }
//                    if (p.getBalance() < 0) {
//                        isBankrupt(p);
//                    }
//                    else {
//                        System.out.println("");
//                        input = "";
//                        while(!input.equals("end")) {
//                            input = decision(p);
//                        }
//                    }
//                }
//                else {
//                    updateCardTiles();
//                    displayAssets(p);
//                    System.out.println("");
//                    promptPlayer(p);
//                    basicTurn(p);
//                    if (p.getBalance() < 0) {
//                        isBankrupt(p);
//                    }
//                    else {
//                        System.out.println("");
//                        input = "";
//                        while(!input.equals("end")) {
//                            input = decision(p);
//                        }
//                    }
//                }
//            }
//        }

    private void isBankrupt(Player p) {
        String input = "";
        if (p.getBalance() < 0) {
            if (checkAssets(p)) {
                System.out.println(p.getName() + " can afford to end bankruptcy without trading");
            }
            else {
                System.out.println(p.getName() + " must trade to avoid bankruptcy");
            }
            if (p.getBalance() < 0) {
                System.out.println(p.getName() + " went bankrupt");
                for (Property s : p.getProperties()) {
                    s.setOwner(null);
                }
                p.setProperties(null);
                itr.remove();
            }
        }
    }

    private boolean checkAssets(Player p) {
        int total = p.getBalance();
        if (p.getHouses() > 0) {
            for (Property s : p.getProperties()) {
                if (s instanceof Street) {
                    if (((Street) s).getHouses() > 0) {
                        total += (((Street) s).getHouseCost() * ((Street) s).getHouses()) / 2;
                    }
                }
            }
        }
        for (Property q : p.getProperties()) {
            total += (q.getCost() / 2);
        }
        if (total >= 0) {
            return true;
        }
        return false;
    }

    public boolean isNumeric(String str) {
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
