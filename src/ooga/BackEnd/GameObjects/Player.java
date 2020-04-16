package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import java.util.ArrayList;
import java.util.List;


public class Player {

    private static final int INITIAL_BALANCE = 1500;
    private static final int BAIL = 50;
    private static final int JAIL_INDEX = 10;

    private final String name;
    private final int playerID;
    private int currentBalance;
    private int currentTile;
    private List<Tile> boardGame;
    private List<Property> properties = new ArrayList<Property>();
    private int houses;

    private boolean isJailed = false;
    private int turnsinJail = 0;
    private int getOutCards = 0;

    public int dice1;
    public int dice2;

    public Player(String name, List<Tile> boardGame){
        this.name = name;
        this.playerID = (int)(Math.random()*100);
        currentBalance = INITIAL_BALANCE;
        currentTile = 0;
        this.boardGame = boardGame;
        this.houses = 0;
    }

    public String getName() {return this.name;}

    public int getHouses() {return this.houses;}

    public int getBalance() {return this.currentBalance;}

    public int getTile() {return this.currentTile;}

    public boolean hasJailFreeCards() {return (this.getOutCards > 0) ? true : false;}

    public void getJailFreeCard() {this.getOutCards += 1;}

    public boolean getJailStatus() {return this.isJailed;}

    public void addJailTurn() {this.turnsinJail += 1;}

    public int getJailTurn() {return this.turnsinJail;}

    public void setFree() {
        this.isJailed = false;
        this.turnsinJail = 0;
    }

    public void setJailed() {
        this.isJailed = true;
        moveTo(JAIL_INDEX);
    }

    public void useJailFreeCard() {
        if (hasJailFreeCards()) {
            this.getOutCards -= 1;
            this.setFree();
        }
    }

    public void payPlayer(Player otherPlayer, int amount) {
        this.currentBalance -= amount;
        otherPlayer.receive(amount);
    }

    public void payBank(int amount) {this.currentBalance -= amount;}

    public void payBail() {payBank(BAIL);}

    public void receive(int amount) {this.currentBalance += amount;}

    public void moveTo(int tile) {
        this.currentTile = tile;
        for (Tile t : this.boardGame) {
            if (t.getBoardIndex() == tile) {
                t.onTile(this);
                t.action();
            }
        }
    }

    public List<Property> getProperties() {return properties;}

    public void addProperty(Property P) {properties.add(P);}

    public void loseProperty(Property P) {properties.remove(P);}

    public void buyProperty(Property P) {
        payBank(P.getCost());
        addProperty(P);
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
            this.payBank(S.getHouseCost() * amount);
            S.addHouse(amount);
            this.houses += amount;
        }
    }

    public void sellHouse(int amount, Street S) {
        this.receive((S.getHouseCost() * amount) / 2);
        S.removeHouse(amount);
        this.houses -= amount;
    }



    public void rollDice() {
        this.dice1 = (int) (Math.random() * 6) + 1;
        this.dice2 = (int) (Math.random() * 6) + 1;
    }

    public void drawCard(String type, ArrayList<Property> props, ArrayList<Player> players) {
        Cards draw = new Cards(type, this, props, players);
        draw.action();
    }
}
