package ooga.BackEnd.GameObjects;

import ooga.BackEnd.GameObjects.Tiles.EventTiles.Event;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Street;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.lang.reflect.Array;
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
    private ArrayList<Tile> boardGame;
    private ArrayList<Property> properties;
    private int houses;

    private boolean isJailed;
    private int turnsinJail;
    private int getOutCards;

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

    }

    public String getName() {return this.name;}

    public int getHouses() {return this.houses;}

    public int getBalance() {return this.currentBalance;}

    public int getTile() {return this.currentTile;}

    public String getTileName() {
        String result = "";
        for (Tile t : this.boardGame) {
            if (t.getBoardIndex() == this.currentTile) {
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
        System.out.println(this.name + " has been jailed.");
        this.isJailed = true;
        this.currentTile = JAIL_INDEX;
        for (Tile t : this.boardGame) {
            if (t.getBoardIndex() == JAIL_INDEX) {
                t.onTile(this);
            }
        }
    }

    public void useJailFreeCard() {
        System.out.println(this.name + " has used a Get Out of Jail Free card.");
        if (hasJailFreeCards()) {
            this.getOutCards -= 1;
            this.setFree();
        }
    }

    public void payPlayer(Player otherPlayer, int amount) {
        System.out.println(this.name + " has paid " + otherPlayer.getName() + " $" + amount);
        this.currentBalance -= amount;
        otherPlayer.receive(amount);
    }

    public void payBank(int amount) {
        System.out.println(this.name + " has paid the bank $" + amount);
        this.currentBalance -= amount;
    }

    public void payBail() {
        System.out.println(this.name + " has paid bail to be free.");
        payBank(BAIL);
    }

    public void receive(int amount) {
        System.out.println(this.name + " has received $" + amount);
        this.currentBalance += amount;
    }

    public void moveTo(int tile) {
        System.out.println(this.name + " just moved to tile " + tile);
        this.currentTile = tile;
        for (Tile t : this.boardGame) {
            if (t.getBoardIndex() == tile) {
                t.onTile(this);
                t.action();
            }
        }
    }

    public List<Property> getProperties() {return properties;}

    public void setProperties(ArrayList<Property> list) {this.properties = list;}

    public void addProperty(Property P) {properties.add(P);}

    public void loseProperty(Property P) {properties.remove(P);}

    public void buyProperty(Property P) {
        if (this.currentBalance > P.getCost()) {
            System.out.println(this.name + " has bought " + P.getTitle());
            payBank(P.getCost());
            addProperty(P);
        }
        else {
            System.out.println(this.name + " cannot afford " + P.getTitle());
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
            this.payBank(S.getHouseCost() * amount);
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
        System.out.println(this.name + " has rolled a " + dice1 + " and a " + dice2 + ", so " + (dice1 + dice2) + " total.");
    }

    public void drawCard(String type, ArrayList<Property> props, ArrayList<Player> players) {
        System.out.println(this.name + " has drawn a " + type + " card.");
        Cards draw = new Cards(type, this, props, players);
        draw.action();
    }
}
