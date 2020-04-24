package ooga.BackEnd.GameObjects;

import javafx.scene.paint.Paint;
import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.api.objects.Card;
import ooga.api.objects.PlayerInfo;
import ooga.util.PropertiesGetter;
import ooga.view.View;
import ooga.view.board.PropertyView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigoaraujo A player class. Able to roll dice, interact with other players, buy property, etc.
 * Implements PlayerInfo interface in order to be able to securely pass information along to the Frontend.
 */

public class Player implements PlayerInfo {

    private static final int INITIAL_BALANCE = 1500;
    private static final int BAIL = 50;
    private static final int JAIL_INDEX = 10;
    private static final List<String> option = List.of("OK");

    private final String name;

    private final int playerID;
    private int currentBalance;
    private int currentTile;
    private List<Tile> boardGame;
    private List<Property> properties;
    private int houses;

    private boolean isJailed;
    private int turnsinJail;
    private int getOutCards;

    private View view;
    private String color;

    public int dice1;
    public int dice2;

    /**
     * Creates the player class. Keeps track of turns in jail, properties,
     * cash balance, houses, tile location, jail status, get out of jail cards,
     * roll dice, trade, and other methods that inpact moving and paying.
     *
     * @param name String value determined before game begins
     * @param boardGame list of all tiles on the board
     */

    public Player(String name, List<Tile> boardGame){
        this.name = name;
        this.boardGame = boardGame;
        this.playerID = (int)(Math.random()*100);
        currentBalance = INITIAL_BALANCE;
        currentTile = 0;
        this.properties = new ArrayList<>();
        this.houses = 0;
        this.isJailed = false;
        this.turnsinJail = 0;
        this.getOutCards = 0;
    }

    @Override
    public List<PropertyView> getPropertiesUnmodifiable() {
        ArrayList<PropertyView> propertiesView = new ArrayList<>();
        for (Property p : properties) {
            propertiesView.add(p.convertView());
        }
        return propertiesView;
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

    @Override
    public String getName() {return this.name;}

    public void setView(View view) {this.view = view;}

    public int getHouses() {return this.houses;}

    public void setColor(String color) {this.color = color;}

    /**
     * Changes player location without triggering action on that
     * tile. Useful for being sent to Jail or when Chance card
     * asks for rent to be calculated differently on utility or
     * railroad tiles.
     *
     * @param tile int value of location on board
     */

    public void setTile(int tile) {
        System.out.println(name + " just moved to tile " + tile);
        currentTile = tile;
        view.movePlayer(this,tile);
        boardGame.get(tile).onTile(this);
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

    /**
     * Moves player to jail without triggering action for jail tile
     */

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

    /**
     * Transaction between this player and another player
     *
     * @param otherPlayer other player that will receive money
     * @param amount int cash sent to other player
     * @param alert whether or not to make a GUI pop up
     */

    public void payPlayer(Player otherPlayer, int amount, Boolean alert) {
        if (alert) {
            Decision d = new Decision("You've paid " + otherPlayer.getName() + " $" + amount, option);
            view.makeUserDecision(d);
        }
        this.currentBalance -= amount;
        otherPlayer.receive(amount);
    }

    /**
     * Transaction between this player and bank
     *
     * @param amount int cash sent to bank
     * @param alert whether or not to make a GUI pop up
     */

    public void payBank(int amount, Boolean alert) {
        if (alert) {
            Decision d = new Decision(PropertiesGetter.getPromptFromKey("playerprompt1") + amount, option);
            view.makeUserDecision(d);
        }
        this.currentBalance -= amount;
    }

    /**
     * Pays bail and appropriate GUI alert pops up
     */

    public void payBail() {
        Decision d = new Decision(PropertiesGetter.getPromptFromKey("playerprompt2"), option);
        view.makeUserDecision(d);
        payBank(BAIL, false);
    }

    /**
     * Player receives cash
     *
     * @param amount int cash received
     */

    public void receive(int amount) {
        System.out.println(this.name + " has received $" + amount);
        this.currentBalance += amount;
    }

    /**
     * Move player to tile on board visually and internally while
     * triggering the action command for the new tile.
     *
     * @param tile int index of new location on board
     */

    public void moveTo(int tile) {
        System.out.println(this.name + " just moved to tile " + tile);
        this.currentTile = tile;
        view.movePlayer(this,tile);
        boardGame.get(tile).onTile(this);
        boardGame.get(tile).action();
    }

    public List<Property> getProperties() {return properties;}

    public void setProperties(List<Property> list) {this.properties = list;}

    /**
     * Buys property if player can afford it
     *
     * @param P property to buy
     */

    public void buyProperty(Property P) {
        if (this.currentBalance > P.getCost()) {
            System.out.println(this.name + " has bought " + P.getTitle());
            payBank(P.getCost(), false);
            properties.add(P);
        }
    }

    /**
     * Checks if player has a monopoly of that property in
     * his or her possession.
     *
     * @param P property to check
     * @return boolean
     */

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

    /**
     * If player has a monopoly of the property and that property has
     * less than 5 houses, this will add a house to it.
     *
     * @param amount number of houses to buy
     * @param S street to buy houses to
     */

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

    /**
     * Sells houses if player has any on that specific
     * property.
     *
     * @param amount number of houses to sell
     * @param S street to sell houses from
     */

    public void sellHouse(int amount, Street S) {
        System.out.println(this.name + " has sold " + amount + " house on " + S.getTitle());
        this.receive((S.getHouseCost() * amount) / 2);
        S.removeHouse(amount);
        this.houses -= amount;
    }

    /**
     * Randomly rolls two dice and updates dice variables in player
     * class.
     */

    public void rollDice() {
        this.dice1 = (int) (Math.random() * 6) + 1;
        this.dice2 = (int) (Math.random() * 6) + 1;
        view.displayRoll(List.of(dice1,dice2));
        System.out.println(this.name + " has rolled a " + dice1 + " and a " + dice2 + ", so " + (dice1 + dice2) + " total.");
    }

    /**
     * Draws card using Card class and triggers the action for that randomly
     * drawn card.
     *
     * @param type type of card to draw ("Community" or "Chance")
     * @param props list of properties
     * @param players list of active players
     */

    public void drawCard(String type, List<Property> props, List<Player> players) {
        System.out.println(this.name + " has drawn a " + type + " card.");
        Cards draw = new Cards(type, this, props, players, this.view);
        draw.action();
    }

    /**
     * Trades money and properties between this player and another
     * player.
     *
     * @param moneyGive money sent to other player
     * @param propertiesGive list of properties sent to other player
     * @param otherPlayer other player in trade
     * @param moneyReceive money received by other player
     * @param propertiesReceive list of properties received by other player
     */

    public void trade(int moneyGive, List<Property> propertiesGive, Player otherPlayer, int moneyReceive, List<Property> propertiesReceive) {
        this.payPlayer(otherPlayer, moneyGive, false);
        otherPlayer.payPlayer(this, moneyReceive, false);
        this.properties.removeAll(propertiesGive);
        List<Property> temp = otherPlayer.getProperties();
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
}
