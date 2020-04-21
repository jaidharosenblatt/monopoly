package ooga.BackEnd.GameLogic;

import java.util.List;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
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
    private Iterator<Player> itr;
    private View view;
    private Player currentPlayer;

    public LoadGame(String game_pathname, int player_number, Stage stage) throws FileNotFoundException, XMLStreamException {
        XMLParser parse = new XMLParser(game_pathname);

        this.allTiles = (ArrayList<Tile>) parse.allTiles.clone();

        createPlayers(player_number);
        currentPlayer = activePlayers.get(0);
        view = new View(stage, this, activePlayers, allTiles);

        for (Tile t : allTiles) {
            t.setView(view);
        }
        for (Player p : activePlayers) {
            p.setView(view);
        }
    }

    private void createPlayers(int player_number) {
        this.activePlayers = new ArrayList<>();
        Player[] temp = new Player[player_number];
        for (int i = 0; i < player_number; i++) {
            //----------------------------------------------
            //INPUT FRONT-END TEXT-FIELD USER-INTERFACE HERE
            //----------------------------------------------
            temp[i] = new Player("Player " + i + 1, this.allTiles);
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
        nextPlayer();
        view.setCurrentPlayer(currentPlayer);
        updateCardTiles();
        rollDiceAndMove(currentPlayer);
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
        int new_tile = p.getTile() + p.dice1 + p.dice2;
        if (new_tile > 39) {
            new_tile -= 40;
            p.receive(200);
        }
        p.moveTo(new_tile);
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
                        System.out.println("Not enough funds");
                        return;
                    }
                }
            }
        }
        if (check < 1) {
            System.out.println("You do not have a monopoly of any property");
            return;
        }
        buildLoop(currentPlayer);
    }

    private void buildLoop(Player p) {
        String test = "";
        while(!test.equals("done")) {
            Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
            System.out.println("What property would you like to build a house on?");
            String input = myObj.nextLine();
            ArrayList<Street> monopoly_set = new ArrayList<>();
            loop:
            for (Property owned : p.getProperties()) {
                if (owned.getTitle().equals(input) && (owned instanceof Street)) {
                    if (owned.isMortgaged()) {
                        System.out.println("can't build a house on a mortgaged property");
                        break loop;
                    }
                    monopoly_set.add((Street) owned);
                    for (Property q : p.getProperties()) {
                        if (owned.getGroupColor().equals(q.getGroupColor()) && (q instanceof Street)) {
                            monopoly_set.add((Street) q);
                        }
                    }
                    for (Street r : monopoly_set) {
                        int diff = ((Street) owned).getHouses() - r.getHouses();
                        if (diff > 0) {
                            System.out.println("Choose another property, must keep house number even");
                            break loop;
                        }
                    }
                    p.buyHouse(1, (Street) owned);
                    test = "done";
                    break loop;
                }
            }
        }
    }

    private String sell(Player p) {
        if (p.getHouses() < 1) {
            System.out.println("You do not have any houses to sell");
            return "";
        }
        sellLoop(p);
        return "";
    }

    private void sellLoop(Player p) {
        String test = "";
        while(!test.equals("done")) {
            Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
            System.out.println("What property would you like to sell a house from?");
            String input = myObj.nextLine();
            ArrayList<Street> monopoly_set = new ArrayList<>();
            loop:
            for (Property owned : p.getProperties()) {
                if (owned.getTitle().equals(input) && (owned instanceof Street)) {
                    monopoly_set.add((Street) owned);
                    for (Property q : p.getProperties()) {
                        if (owned.getGroupColor().equals(q.getGroupColor()) && (q instanceof Street)) {
                            monopoly_set.add((Street) q);
                        }
                    }
                    for (Street r : monopoly_set) {
                        int diff = ((Street) owned).getHouses() - r.getHouses();
                        if (diff < 0) {
                            System.out.println("Choose another property, must keep house number even");
                            break loop;
                        }
                    }
                    p.sellHouse(1, (Street) owned);
                    test = "done";
                    break loop;
                }
            }
        }
    }

    private String mortgage(Player p) {
        if (p.getProperties().size() < 1) {
            System.out.println("You do not have any properties to mortgage");
            return "";
        }
        String test = "";
        while(!test.equals("done")) {
            Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
            System.out.println("Which property would you like to mortgage?");
            String input = myObj.nextLine();
            for (Property s : p.getProperties()) {
                if (s.getTitle().equals(input) && p.getProperties().contains(s) && !(s.isMortgaged())) {
                    s.setMortgaged();
                    test = "done";
                    break;
                }
            }
        }
        return "";
    }

    private String unmortgage(Player p) {
        String test = "";
        while(!test.equals("done")) {
            Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
            System.out.println("Which property would you like to unmortgage?");
            String input = myObj.nextLine();
            for (Property s : p.getProperties()) {
                if (s.getTitle().equals(input) && p.getProperties().contains(s) && s.isMortgaged()) {
                    s.liftMortgage();
                    test = "done";
                    break;
                }
            }
        }
        return "";
    }

    private String trade(Player p) {
        String test = "";
        for (Player a : activePlayers) {
            if (p != a) {
                System.out.print(a.getName() + " ");
            }
        }
        System.out.println("");
        while(!test.equals("done")) {
            System.out.println("Which player would you like to trade with?");
            Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
            String input = myObj.nextLine();
            for (Player b : activePlayers) {
                if (b.getName().equals(input)) {
                    System.out.println("List properties you want and cash: Ex. [prop,prop,...,200]");
                    Scanner myObj2 = new Scanner(System.in); //replace this with front-end decision instead
                    String input2 = myObj2.nextLine();
                    String[] want = input2.split(",");
                    System.out.println("List properties and cash amount you will give: Ex. [prop,prop,...,200]");
                    Scanner myObj3 = new Scanner(System.in); //replace this with front-end decision instead
                    String input3 = myObj3.nextLine();
                    String[] give = input3.split(",");
                    int cashWant = Integer.parseInt(want[want.length - 1]);
                    int cashGive = Integer.parseInt(give[give.length - 1]);
                    ArrayList<Property> propWant = new ArrayList<>();
                    ArrayList<Property> propGive = new ArrayList<>();
                    for (Property q : this.properties) {
                        for (String s : want) {
                            if (s.equals(q.getTitle())) {
                                propWant.add(q);
                            }
                        }
                    }
                    for (Property q : this.properties) {
                        for (String s : give) {
                            if (s.equals(q.getTitle())) {
                                propGive.add(q);
                            }
                        }
                    }
                    if (!(p.getProperties().containsAll(propGive)) || !(b.getProperties().containsAll(propWant)) || p.getBalance() < cashGive || b.getBalance() < cashWant) {
                        System.out.println("Trade is not possible");
                        return "";
                    }
                    System.out.println("Does " + b.getName() + " accept this trade? [Y or N]");
                    Scanner myObj4 = new Scanner(System.in); //replace this with front-end decision instead
                    String input4 = myObj4.nextLine();
                    if (input4.equals("Y")) {
                        System.out.println(p.getName() + " has successfully traded with " + b.getName());
                        p.trade(cashGive, propGive, b, cashWant, propWant);
                        test = "done";
                        break;
                    }
                    System.out.println("Trade not successful");
                    return "";
                }
            }
        }
        return "";
    }

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

}
