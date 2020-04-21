package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameLogic.Decision;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.view.Card;
import ooga.api.view.PlayerInfo;
import ooga.view.View;
import ooga.view.board.PropertyView;

import java.util.ArrayList;
import java.util.List;


public class Player implements PlayerInfo {

    private static final int INITIAL_BALANCE = 1500;
    private static final int BAIL = 50;
    private static final int JAIL_INDEX = 10;
    private static final List<String> option = List.of("OK");

    private final String name;
    private final int playerID;
    private int currentBalance;
    private int currentTile;
    private ArrayList<Tile> boardGame;
    private ArrayList<Property> properties;
    private int houses;

    private boolean isJailed;
    private int turnsinJail;
    private int getOutCards;

    private View view;
    private String color;

    public int dice1;
    public int dice2;

    public Player(String name, ArrayList<Tile> boardGame){
        this.name = name;
        this.playerID = (int)(Math.random()*100);
        currentBalance = INITIAL_BALANCE;
        currentTile = 0;
        this.boardGame = boardGame;
        this.properties = new ArrayList<>();
        this.houses = 0;
        this.isJailed = false;
        this.turnsinJail = 0;
        this.getOutCards = 0;
        this.color = "";
    }

    public String getName() {return this.name;}

    public int getHouses() {return this.houses;}

    public int getBalance() {return this.currentBalance;}

    public void setColor(String c) {this.color = c;}

    public int getTile() {return this.currentTile;}

    public void setTile(int tile) {
        System.out.println(name + " just moved to tile " + tile);
        currentTile = tile;
        boardGame.get(tile).onTile(this);
    }

    public String getTileName() {
        String result = "";
        for (Tile t : boardGame) {
            if (t.getBoardIndex() == currentTile) {
                if (t instanceof Event) {
                    result = ((Event) t).getName();
                }
                if (t instanceof Property) {
                    result = ((Property) t).getTitle();
                }
            }
        }
        return result;
    }

    public boolean hasJailFreeCards() {return (this.getOutCards > 0) ? true : false;}

    public void getJailFreeCard() {this.getOutCards += 1;}

    public boolean isJailed() {return this.isJailed;}

    public int getNumJFC() {return this.getOutCards;}

    public void addJailTurn() {this.turnsinJail += 1;}

    public int getJailTurn() {return this.turnsinJail;}

    public void setFree() {
        System.out.println(this.name + " has been set free.");
        this.isJailed = false;
        this.turnsinJail = 0;
    }

    public void setJailed() {
        Decision d = new Decision("You've been jailed!", option);
        view.makeUserDecision(d);
        this.isJailed = true;
        this.currentTile = JAIL_INDEX;
        boardGame.get(JAIL_INDEX).onTile(this);
        view.movePlayer(this,JAIL_INDEX);
    }

    public void useJailFreeCard() {
        System.out.println(this.name + " has used a Get Out of Jail Free card.");
        if (hasJailFreeCards()) {
            this.getOutCards -= 1;
            this.setFree();
        }
    }

    public void payPlayer(Player otherPlayer, int amount, Boolean alert) {
        if (alert) {
            Decision d = new Decision("You've paid " + otherPlayer.getName() + " $" + amount, option);
            view.makeUserDecision(d);
        }
        this.currentBalance -= amount;
        otherPlayer.receive(amount);
    }

    public void payBank(int amount, Boolean alert) {
        if (alert) {
            Decision d = new Decision("You've paid the bank $" + amount, option);
            view.makeUserDecision(d);
        }
        this.currentBalance -= amount;
    }

    public void payBail() {
        Decision d = new Decision("You've paid $50 for bail", option);
        view.makeUserDecision(d);
        payBank(BAIL, false);
    }

    public void receive(int amount) {
        System.out.println(this.name + " has received $" + amount);
        this.currentBalance += amount;
    }

    public void moveTo(int tile) {
        System.out.println(this.name + " just moved to tile " + tile);
        this.currentTile = tile;
        view.movePlayer(this,tile);
        boardGame.get(tile).onTile(this);
        boardGame.get(tile).action();
    }

    public ArrayList<Property> getProperties() {return properties;}

    public void setProperties(ArrayList<Property> list) {this.properties = list;}

    public void addProperty(Property P) {properties.add(P);}

    public void loseProperty(Property P) {properties.remove(P);}

    public void buyProperty(Property P) {
        if (this.currentBalance > P.getCost()) {
            System.out.println(this.name + " has bought " + P.getTitle());
            payBank(P.getCost(), false);
            addProperty(P);
        }
    }

    public boolean hasMonopoly(Property P) {
        int total = 0;
        for (Property prop : this.properties) {
            if (prop.getGroupColor().equals(P.getGroupColor())) {
                total++;
            }
        }
        if (total == P.getGroupNumber()) {
            return true;
        }
        return false;
    }

    public void buyHouse(int amount, Street S) {
        if (this.hasMonopoly(S) && S.getHouses() + amount <= 5) {
            System.out.println(this.name + " has bought " + amount + " house on " + S.getTitle());
            this.payBank(S.getHouseCost() * amount, false);
            S.addHouse(amount);
            this.houses += amount;
        }
        else {
            System.out.println("That's too many houses");
        }
    }

    public void sellHouse(int amount, Street S) {
        System.out.println(this.name + " has sold " + amount + " house on " + S.getTitle());
        this.receive((S.getHouseCost() * amount) / 2);
        S.removeHouse(amount);
        this.houses -= amount;
    }



    public void rollDice() {
        this.dice1 = (int) (Math.random() * 6) + 1;
        this.dice2 = (int) (Math.random() * 6) + 1;
        view.displayRoll(List.of(dice1,dice2));
        System.out.println(this.name + " has rolled a " + dice1 + " and a " + dice2 + ", so " + (dice1 + dice2) + " total.");
    }

    public void drawCard(String type, ArrayList<Property> props, ArrayList<Player> players) {
        System.out.println(this.name + " has drawn a " + type + " card.");
        Cards draw = new Cards(type, this, props, players, this.view);
        draw.action();
    }

    public void trade(int moneyGive, ArrayList<Property> propertiesGive, Player otherPlayer, int moneyReceive, ArrayList<Property> propertiesReceive) {
        this.payPlayer(otherPlayer, moneyGive, false);
        otherPlayer.payPlayer(this, moneyReceive, false);
        this.properties.removeAll(propertiesGive);
        ArrayList<Property> temp = otherPlayer.getProperties();
        temp.removeAll(propertiesReceive);
        for (Property p : propertiesGive) {
            p.setOwner(otherPlayer);
        }
        for (Property p : propertiesReceive) {
            p.setOwner(this);
        }
        this.properties.addAll(propertiesReceive);
        temp.addAll(propertiesGive);
        otherPlayer.setProperties(temp);
    }

    public View getView() {return view;}

    public void setView(View view) {this.view = view;}

    @Override
    public List<PropertyView> getPropertiesUnmodifiable() {
        ArrayList<PropertyView> propertiesView = new ArrayList<>();
        for (Property p : properties) {
            propertiesView.add(p.convertView());
        }
        return propertiesView;
    }

    @Override
    public List<Card> getHeldCardsUnmodifiable() {
        return null;
    }

    @Override
    public Integer getCashBalance() {
        return currentBalance;
    }

    @Override
    public Integer getPositionOnBoard() {
        return currentTile;
    }

    @Override
    public int getPlayerId() {
        return playerID;
    }

    @Override
    public String getPlayerColor() {
        return color;
    }
}
